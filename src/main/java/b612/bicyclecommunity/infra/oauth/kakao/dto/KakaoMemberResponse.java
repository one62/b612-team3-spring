package b612.bicyclecommunity.infra.oauth.kakao.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import b612.bicyclecommunity.domain.OauthId;
import b612.bicyclecommunity.domain.OauthMember;
import b612.bicyclecommunity.domain.OauthServerType;

@JsonNaming(SnakeCaseStrategy.class)
public record KakaoMemberResponse(
	Long id,
	boolean hasSignedUp,
	LocalDateTime connectedAt,
	KakaoAccount kakaoAccount
) {
	public OauthMember toDomain() {
		return OauthMember.builder()
			.oauthId(new OauthId(String.valueOf(id), OauthServerType.KAKAO))
			.nickname(kakaoAccount.profile.nickname)
			.profileImageUrl(kakaoAccount.profile.profileImageUrl)
			.build();
	}

	@JsonNaming(SnakeCaseStrategy.class)
	public record KakaoAccount(
		boolean profileNeedsAgreement,
		boolean profileNicknameNeedsAgreement,
		boolean profileImageNeedsAgreement,
		Profile profile,
		boolean nameNeedsAgreement,
		String name,
		boolean emailNeedsAgreement,
		boolean isEmailValid,
		boolean isEmailVerified,
		String email,
		boolean ageRangeNeedsAgreement,
		String ageRange,
		boolean birthyearNeedsAgreement,
		String birthyear,
		boolean birthdayNeedsAgreement,
		String birthday,
		String birthdayType,
		boolean genderNeedsAgreement,
		String gender,
		boolean phoneNumberNeedsAgreement,
		String phoneNumber,
		boolean ciNeedsAgreement,
		String ci,
		LocalDateTime ciAuthenticatedAt
	) {}

	@JsonNaming(SnakeCaseStrategy.class)
	public record Profile(
		String nickname,
		String thumbnailImageUrl,
		String profileImageUrl,
		boolean isDefaultImage
	) {}
}
