package b612.bicyclecommunity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import b612.bicyclecommunity.dto.req.CourseSaveReq;
import b612.bicyclecommunity.global.security.UserDetailsImpl;
import b612.bicyclecommunity.service.CourseService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RequestMapping(value = "/course")
@RestController
@RequiredArgsConstructor
public class CourseController {

	private final CourseService courseService;
	
	@PostMapping("/save")
	public ResponseEntity<?> postNewCourse(@RequestBody CourseSaveReq courseSaveReq) throws IOException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		// courseService.saveCourse(
		// 	userDetails.getUserId(),
		// 	courseSaveReq.getMeter(),
		// 	courseSaveReq.getCourseArray());
		try {
        	Integer i = courseService.saveCourse(userDetails.getUserId(), courseSaveReq.getMeter(), courseSaveReq.getCourseArray());
        	return ResponseEntity.ok().body("신규 코스 " + i.toString() + " 저장 완료");
    	} catch (IOException e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("코스 저장 중 오류 발생");
    	}
		// return ResponseEntity.ok().body("신규 코스 저장 완료");
	}

	@GetMapping("/courses/{courseId}")
	public Flux<Pair<Double, Double>> getCourse(@PathVariable Integer courseId) throws CsvValidationException, FileNotFoundException, IOException {
		List<Pair<Double, Double>> courseArray = courseService.loadCourseArray(courseId);
		return Flux.fromIterable(courseArray);
	}
	
	
	
}
