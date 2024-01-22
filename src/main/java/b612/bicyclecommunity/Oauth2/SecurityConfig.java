package b612.bicyclecommunity.Oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests((authorizeHttpRequests) ->
				authorizeHttpRequests
					.requestMatchers("/user/**").authenticated()
					.anyRequest().permitAll())
			.formLogin((formLogin) ->
				formLogin
					.loginPage("/loginForm")
					.loginProcessingUrl("/login")
					.defaultSuccessUrl("/")
			.oauth2Login((oauth2Login) ->
				oauth2Login
					.loginPage("/loginForm")
					.defaultSuccessUrl("/")
					.userInfoEndpoint((userInfoEndpoint) ->
						userInfoEndpoint
							.userService(principalOauth2UserService))
					.userService(principalOauth2UserService)
			));
		
		return http.build();
	}
}
