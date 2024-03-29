package b612.bicyclecommunity.controller;

import b612.bicyclecommunity.dto.team.res.SearchRes;
import b612.bicyclecommunity.dto.user.req.MobileKaKaoLoginReq;
import b612.bicyclecommunity.dto.user.req.UserEditReq;
import b612.bicyclecommunity.dto.user.res.TokenRes;
import b612.bicyclecommunity.dto.user.res.UserInfoRes;
import b612.bicyclecommunity.global.security.UserDetailsImpl;
import b612.bicyclecommunity.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/mobile/kakao")
    public ResponseEntity<TokenRes> mobileKakao(@RequestBody MobileKaKaoLoginReq mobileKaKaoLoginReq){
        TokenRes tokenRes = userService.mobileKakao(mobileKaKaoLoginReq.getId());

        return ResponseEntity.ok().body(tokenRes);
    }

    @GetMapping("/info")
    public ResponseEntity<UserInfoRes> info(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        UserInfoRes userInfoRes = userService.info(userDetails.getUserId());

        return ResponseEntity.ok().body(userInfoRes);
    }

    @PostMapping("/edit")
    public ResponseEntity<UserInfoRes> edit(@RequestBody @Valid UserEditReq userEditReq){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        UserInfoRes userInfoRes = userService.edit(
                userDetails.getUserId(),
                userEditReq.getAge(),
                userEditReq.getName(),
                userEditReq.getAddress(),
                userEditReq.getGender()
        );

        return ResponseEntity.ok().body(userInfoRes);
    }

    @GetMapping("/team")
    public ResponseEntity<?> teams(@RequestParam("page") int page,
                                  @RequestParam("size") int size){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<b612.bicyclecommunity.dto.team.res.SearchRes> teams = userService.teams(userDetails.getUserId(), page, size);

        return ResponseEntity.ok().body(teams);
    }
}
