package b612.bicyclecommunity.infra.oauth.kakao.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import b612.bicyclecommunity.infra.oauth.kakao.dto.KakaoMemberResponse;
import b612.bicyclecommunity.infra.oauth.kakao.dto.KakaoToken;

public interface KakaoApiClient {
	
	@PostExchange(url = "https://kauth.kakao.com/oauth/token", contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	KakaoToken fetchToken (@RequestParam MultiValueMap<String, String> params);

	@GetExchange(url = "https://kapi.kakao.com/v2/user/me")
	KakaoMemberResponse fetchMember (@RequestHeader(name = HttpHeaders.AUTHORIZATION) String bearerToken);
}
