package b612.bicyclecommunity.domain.client;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import b612.bicyclecommunity.domain.OauthMember;
import b612.bicyclecommunity.domain.OauthServerType;

@Component
public class OauthMemberClientComposite {
	
	private final Map<OauthServerType, OauthMemberClient> mapping;

	public OauthMemberClientComposite(Set<OauthMemberClient> clients) {
		mapping = clients.stream()
			.collect(Collectors.toMap(
				OauthMemberClient::supportServer,
				Function.identity()
			));
	}

	public OauthMember fetch(OauthServerType serverType, String authCode) {
		return getClient(serverType).fetch(authCode);
	}

	private OauthMemberClient getClient(OauthServerType serverType) {
		return Optional.ofNullable(mapping.get(serverType))
			.orElseThrow(() -> new IllegalArgumentException("지원하지 않는 서버입니다."));
	}
}
