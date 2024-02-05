package b612.bicyclecommunity.controller;

import b612.bicyclecommunity.dto.req.CourseUserSaveReq;
import b612.bicyclecommunity.global.security.UserDetailsImpl;
import b612.bicyclecommunity.service.CourseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/courseuser")
@RequiredArgsConstructor
public class CourseUserController {

    private final CourseUserService courseUserService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CourseUserSaveReq courseUserSaveReq){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        courseUserService.save(
                userDetails.getUserId(),
                courseUserSaveReq.getCourseName(),
                courseUserSaveReq.getStart(),
                courseUserSaveReq.getEnd(),
                courseUserSaveReq.getRate(),
                courseUserSaveReq.getComment(),
                courseUserSaveReq.getAverageSpeed(),
                courseUserSaveReq.getRestMinute()
        );

        return ResponseEntity.ok().body("주행기록 저장 완료");
    }
}