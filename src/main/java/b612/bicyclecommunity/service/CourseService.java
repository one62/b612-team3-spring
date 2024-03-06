package b612.bicyclecommunity.service;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import b612.bicyclecommunity.domain.course.Course;
import b612.bicyclecommunity.domain.user.User;
import b612.bicyclecommunity.repository.CourseRepository;
import b612.bicyclecommunity.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseService {
	private final CourseRepository courseRepository;
	private final UserRepository userRepository;

	@Transactional
	public Integer saveCourse(String createdUserID, String name, String startTime, String endTime, int elapsedTime,
	Double totalTravelDistance, String encodedPolyline, List<Double> startLatLng, List<Double> endLatLng, List<Double> centerLatLng,
	List<Double> southwestLatLng, List<Double> northeastLatLng, Double zoom, int rating, int difficulty, String review, Boolean publicCourse) {

		// course 생성 후 정보 저장
		User createdUser = userRepository.findById(createdUserID).orElseThrow();

		Course course = Course.createCourse(
			createdUser, name, startTime, endTime, elapsedTime, totalTravelDistance, startLatLng,
			endLatLng, centerLatLng, southwestLatLng, northeastLatLng, zoom, rating, difficulty, review, publicCourse
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

	public List<Course> loadCourseList() {
		return courseRepository.findAll();
	}

	public String loadEncodedPolyline(Integer courseId) {
		Course course = courseRepository.findById(courseId).orElseThrow();

		String filename = course.getFileUrl();
		StringBuilder builder = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
}