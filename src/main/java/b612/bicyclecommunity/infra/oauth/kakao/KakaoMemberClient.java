package b612.bicyclecommunity.infra.oauth.kakao;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import b612.bicyclecommunity.domain.OauthMember;
import b612.bicyclecommunity.domain.OauthServerType;
import b612.bicyclecommunity.domain.client.OauthMemberClient;
import b612.bicyclecommunity.infra.oauth.kakao.client.KakaoApiClient;
import b612.bicyclecommunity.infra.oauth.kakao.dto.KakaoMemberResponse;
import b612.bicyclecommunity.infra.oauth.kakao.dto.KakaoToken;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KakaoMemberClient implements OauthMemberClient{
	
	private final KakaoApiClient kakaoApiClient;
	private final KakaoOauthConfig kakaoOauthConfig;

	@Override
	public OauthServerType supportServer() {
		return OauthServerType.KAKAO;
	}

	@Override
	public OauthMember fetch(String authCode) {
		KakaoToken token = kakaoApiClient.fetchToken(tokenRequsetParams(authCode));
		KakaoMemberResponse kakaoMemberResponse = kakaoApiClient.fetchMember("Bearer " + token.accessToken());
		return kakaoMemberResponse.toDomain();
	}

	private MultiValueMap<String, String> tokenRequsetParams(String authCode) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", kakaoOauthConfig.clientId());
		params.add("redirect_uri", kakaoOauthConfig.redirectUri());
		params.add("code", authCode);
		params.add("client_secret", kakaoOauthConfig.clientSecret());
		return params;
	}
}
