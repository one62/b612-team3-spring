package b612.bicyclecommunity.domain.client;

import b612.bicyclecommunity.domain.OauthMember;
import b612.bicyclecommunity.domain.OauthServerType;

public interface OauthMemberClient {
	
	OauthServerType supportServer();

	OauthMember fetch(String code);
}
