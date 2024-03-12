package b612.bicyclecommunity.service;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Optional;

import b612.bicyclecommunity.domain.course.Course;
import b612.bicyclecommunity.domain.courseUser.CourseUser;
import b612.bicyclecommunity.domain.user.User;
import b612.bicyclecommunity.dto.course.res.CourseRes;
import b612.bicyclecommunity.repository.CourseRepository;
import b612.bicyclecommunity.repository.CourseUserRepository;
import b612.bicyclecommunity.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseService {
	private final CourseRepository courseRepository;
	private final UserRepository userRepository;
	private final CourseUserRepository courseUserRepository;

	@Transactional
	public Integer saveCourse(String createdUserID, String name,
	Double totalTravelDistance, String encodedPolyline, List<Double> startLatLng, List<Double> endLatLng, List<Double> centerLatLng,
	List<Double> southwestLatLng, List<Double> northeastLatLng, Double zoom, Boolean publicCourse, Boolean original) {

		// course 생성 후 정보 저장
		User createdUser = userRepository.findById(createdUserID).orElseThrow();

		Course course = Course.createCourse(
			createdUser, name, totalTravelDistance, startLatLng,
			endLatLng, centerLatLng, southwestLatLng, northeastLatLng, zoom, publicCourse, original
		);
		courseRepository.saveAndFlush(course);

		// 파일로 저장
		String filename = "/b612bicycle/courses/" + course.getId().toString();
		try (FileWriter writer = new FileWriter(filename)) {
			writer.write(encodedPolyline);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 파일 경로 저장 후 ID 반환
		course.setFileUrl(filename);
		courseRepository.save(course);
		return course.getId();
	}


	public String loadEncodedPolyline(Integer courseId) {
		Course course = courseRepository.findById(courseId)
			.orElseThrow(() -> new IllegalStateException(courseId + "course not found"));

		String filename = course.getFileUrl();
		
		try {
			return new String(Files.readAllBytes(Paths.get(filename)));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("파일 읽기 실패", e);
		}
	}
	

	private record courseResData(
		Integer reviewCount, Integer avgElapsedTime, Double avgRating, Double avgDifficulty
	) {}

	private courseResData getCourseResData(Integer courseId) {
		// 리뷰 개수, 평균 주행 시간, 평균 별점, 평균 난이도
		// courseuser에서 courseId로 조회
		List<CourseUser> courseUsers = courseUserRepository.findByCourseId(courseId).orNull();
		
		Integer reviewCount = courseUsers.size();
		Integer avgElapsedTime = courseUsers.stream().mapToInt(CourseUser::getElapsedTime).sum() / reviewCount;
		Double avgRating = courseUsers.stream().mapToDouble(CourseUser::getRating).average().orElse(0);
		Double avgDifficulty = courseUsers.stream().mapToDouble(CourseUser::getDifficulty).average().orElse(0);

		return new courseResData(reviewCount, avgElapsedTime, avgRating, avgDifficulty);
	}


	public List<CourseRes> loadCoursesRes(String createdUserID) {
		List<Course> courses = courseRepository.findByCreatedUserAndOriginal(userRepository.findById(createdUserID).orElseThrow(), true);
		List<CourseRes> courseResList = new ArrayList<>();
		for (Course course : courses) {
			courseResData data = getCourseResData(course.getId());
			courseResList.add(new CourseRes(
				course.getId(), course.getName(), course.getCreatedUser(), course.getOriginal(),
				course.getTotalTravelDistance(), course.getZoom(), course.getPublicCourse(),
				course.getStartLatLng(), course.getEndLatLng(), course.getCenterLatLng(),
				course.getSouthwestLatLng(), course.getNortheastLatLng(),
				data.reviewCount, data.avgElapsedTime, data.avgRating, data.avgDifficulty
			));
		}
		return courseResList;
	}


}