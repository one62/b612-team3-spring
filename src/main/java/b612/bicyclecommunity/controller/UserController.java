package b612.bicyclecommunity.controller;

import b612.bicyclecommunity.dto.req.MobileKaKaoLoginReq;
import b612.bicyclecommunity.dto.res.TokenResponse;
import b612.bicyclecommunity.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/mobile/kakao")
    public ResponseEntity<TokenResponse> mobileKakao(@RequestBody MobileKaKaoLoginReq mobileKaKaoLoginReq){
        TokenResponse tokenResponse = userService.mobileKakao(mobileKaKaoLoginReq.getId());

        return ResponseEntity.ok().body(tokenResponse);
    }
}
