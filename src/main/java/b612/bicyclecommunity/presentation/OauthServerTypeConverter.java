package b612.bicyclecommunity.presentation;


import org.springframework.core.convert.converter.Converter;

import b612.bicyclecommunity.domain.OauthServerType;


public class OauthServerTypeConverter implements Converter<String, OauthServerType>{
	
	@Override
	public OauthServerType convert(String source) {
		return OauthServerType.fromName(source);
	}
}
