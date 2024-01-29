package b612.bicyclecommunity.domain;

public enum OauthServerType {
	
	KAKAO,
	;

	public static OauthServerType fromName(String type) {
		return OauthServerType.valueOf(type.toUpperCase());
	}
}
