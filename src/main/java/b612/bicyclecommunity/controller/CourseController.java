package b612.bicyclecommunity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.NoSuchElementException;

import b612.bicyclecommunity.dto.course.req.CourseSaveReq;
import b612.bicyclecommunity.global.security.UserDetailsImpl;
import b612.bicyclecommunity.service.CourseService;
import b612.bicyclecommunity.service.CourseUserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequestMapping(value = "/course")
@RestController
@RequiredArgsConstructor
public class CourseController {

	private final CourseService courseService;
	private final CourseUserService courseUserService;
	

	// 새 코스 저장. 자신이 새로 생성한 코스일 경우 original = true
	@PostMapping("/save")
	public ResponseEntity<?> postNewCourse(@RequestBody CourseSaveReq courseSaveReq) throws IOException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();


       	Integer newCourseId = courseService.saveCourse(
			userDetails.getUserId(),
			courseSaveReq.getName(), 
			courseSaveReq.getTotalTravelDistance(), courseSaveReq.getEncodedPolyline(), courseSaveReq.getStartLatLng(),
			courseSaveReq.getEndLatLng(), courseSaveReq.getCenterLatLng(), courseSaveReq.getSouthwestLatLng(),
			courseSaveReq.getNortheastLatLng(), courseSaveReq.getZoom(),
			courseSaveReq.getPublicCourse(), courseSaveReq.getOriginal()
		);

		courseUserService.saveCourseUser(
            userDetails.getUserId(),
            newCourseId,
            courseSaveReq.getStartTime(),
            courseSaveReq.getEndTime(),
            courseSaveReq.getElapsedTime(),
            courseSaveReq.getRating(),
            courseSaveReq.getDifficulty(),
            courseSaveReq.getReview()
        );

       	return ResponseEntity.ok().body("신규 코스 " + newCourseId.toString() + " 저장 완료");
	}


	// 코스 ID로 encodedPolyline 불러오기
	@GetMapping("/courses/{courseId}")
	public ResponseEntity<String> getCourse(@PathVariable Integer courseId) {
		try {
			String encodedPolyline = courseService.loadEncodedPolyline(courseId);
			return ResponseEntity.ok(encodedPolyline);
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("에러 : " + e.getMessage() + e.toString());
		}
	}
	

	/*
	 * 1. 자신이 새로 생성한 코스만 조회
	 * 	createdUser가 자신이고, original이 true인 경우
	 * 2. 다른 사람의 코스를 따라 주행한 코스도 조회
	 * 	createdUser가 자신이 아니고, original이 true이며, courseuser에 자신의 id가 있는 경우
	 * 3. 자신이 주행한 적 없는 공개 코스도 조회
	 * 	createdUser가 자신이 아니고, original이 true이며, publicCourse가 true인 경우
	 * + 정렬 순서? 페이지?
	 */
	
	// 1. 자신이 생성한 코스만 조회. original = true.
	@GetMapping("/my")
	public ResponseEntity<?> getMyCourses() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		try {
			return ResponseEntity.ok().body(courseService.loadCourseListByCreatedUserOriginal(userDetails.getUserId()));
		} catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("코스 불러오기 중 오류 발생");
    	}
	}
}
