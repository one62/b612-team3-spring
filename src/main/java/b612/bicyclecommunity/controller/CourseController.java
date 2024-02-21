package b612.bicyclecommunity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import b612.bicyclecommunity.dto.req.CourseSaveReq;
import b612.bicyclecommunity.global.security.UserDetailsImpl;
import b612.bicyclecommunity.service.CourseService;
import lombok.RequiredArgsConstructor;
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
	
	@PostMapping("/save")
	public ResponseEntity<?> postNewCourse(@RequestBody CourseSaveReq courseSaveReq) throws IOException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();


       	Integer newCourseId = courseService.saveCourse(
			userDetails.getUserId(),
			courseSaveReq.getName(), courseSaveReq.getStartTime(), courseSaveReq.getEndTime(), courseSaveReq.getElapsedTime(),
			courseSaveReq.getTotalTravelDistance(), courseSaveReq.getEncodedPolyline(), courseSaveReq.getStartLatLng(),
			courseSaveReq.getEndLatLng(), courseSaveReq.getCenterLatLng(), courseSaveReq.getSouthwestLatLng(),
			courseSaveReq.getNortheastLatLng(), courseSaveReq.getZoom(), courseSaveReq.getRating(), courseSaveReq.getDifficulty(),
			courseSaveReq.getReview(), courseSaveReq.getPublicCourse()
		);
       	return ResponseEntity.ok().body("신규 코스 " + newCourseId.toString() + " 저장 완료");
	}

	@GetMapping("/courses/{courseId}")
	public ResponseEntity<String> getCourse(@PathVariable Integer courseId) {
		return ResponseEntity.ok(courseService.loadEncodedPolyline(courseId));
	}
	
	
	
}
