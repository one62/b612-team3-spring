package b612.bicyclecommunity.domain.authcode;

import b612.bicyclecommunity.domain.OauthServerType;

public interface AuthCodeRequestUrlProvider {
	
	OauthServerType supportServer();

	String provide();
}
