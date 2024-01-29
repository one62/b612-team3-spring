package b612.bicyclecommunity.infra.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpExchangeAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import b612.bicyclecommunity.infra.oauth.kakao.client.KakaoApiClient;

@Configuration
public class HttpInterfaceConfig {

	@Bean
	public KakaoApiClient kakaoApiClient () {
		return createHttpInterface(KakaoApiClient.class);
	}

	private <T> T createHttpInterface (Class<T> clazz) {
		WebClient webClient = WebClient.create();
		HttpServiceProxyFactory build = HttpServiceProxyFactory
			.builderFor(WebClientAdapter.create(webClient)).build();
		return build.createClient(clazz);
	}
	
}
