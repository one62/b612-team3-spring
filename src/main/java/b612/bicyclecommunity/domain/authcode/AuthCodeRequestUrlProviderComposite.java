package b612.bicyclecommunity.domain.authcode;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import b612.bicyclecommunity.domain.OauthServerType;

@Component
public class AuthCodeRequestUrlProviderComposite {

	private final Map<OauthServerType, AuthCodeRequestUrlProvider> mapping;

	public AuthCodeRequestUrlProviderComposite(Set<AuthCodeRequestUrlProvider> providers) {
		mapping = providers.stream()
			.collect(Collectors.toMap(
				AuthCodeRequestUrlProvider::supportServer,
				Function.identity()
			));
	}

	public String provide(OauthServerType type) {
		return getProvider(type).provide();
	}

	private AuthCodeRequestUrlProvider getProvider(OauthServerType type) {
		return Optional.ofNullable(mapping.get(type))
			.orElseThrow(() -> new IllegalArgumentException("지원하지 않는 서버입니다."));
	}
	
}
