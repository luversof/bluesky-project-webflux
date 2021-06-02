package net.luversof.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.stereotype.Component;

import io.github.luversof.boot.autoconfigure.security.reactive.SecurityWebFilterChainCustomizer;
import net.luversof.security.oauth2.client.BlueskyReactiveOAuth2AuthorizedClientService;

@Component
public class Oauth2SecurityWebFilterChainCustomizer implements SecurityWebFilterChainCustomizer {

	@Autowired
	private BlueskyReactiveOAuth2AuthorizedClientService blueskyReactiveOAuth2AuthorizedClientService;

	@Override
	public void postConfigure(ServerHttpSecurity http) {
		http.oauth2Login().authorizedClientService(blueskyReactiveOAuth2AuthorizedClientService);
	}
	
}
