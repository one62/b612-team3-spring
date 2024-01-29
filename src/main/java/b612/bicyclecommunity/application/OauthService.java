package b612.bicyclecommunity.application;

import org.springframework.stereotype.Service;

import b612.bicyclecommunity.domain.OauthMember;
import b612.bicyclecommunity.domain.OauthMemberRepository;
import b612.bicyclecommunity.domain.OauthServerType;
import b612.bicyclecommunity.domain.authcode.AuthCodeRequestUrlProviderComposite;
import b612.bicyclecommunity.domain.client.OauthMemberClientComposite;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OauthService {

	private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
	private final OauthMemberClientComposite oauthMemberClientComposite;
	private final OauthMemberRepository oauthMemberRepository;

	public String getAuthCodeRequestUrl(OauthServerType type) {
		return authCodeRequestUrlProviderComposite.provide(type);
	}
	
	public Long login(OauthServerType type, String authCode) {
		OauthMember oauthMember = oauthMemberClientComposite.fetch(type, authCode);
		OauthMember saved = oauthMemberRepository.findByOauthId(oauthMember.oauthId())
			.orElseGet(() -> oauthMemberRepository.save(oauthMember)); // 가입되어 있지 않으면 가입
		return saved.id(); //JWT 사용 시 ID 대신 JWT Access Token을 반환
	}
}
