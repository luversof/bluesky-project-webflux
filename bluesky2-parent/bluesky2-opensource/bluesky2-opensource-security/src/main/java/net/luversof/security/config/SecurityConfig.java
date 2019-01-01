package net.luversof.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.web.server.AuthenticatedPrincipalServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;

import net.luversof.security.core.userdetails.BlueskyUserDetailsService;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private BlueskyUserDetailsService userDetailsService;
	
	@Autowired
	private ServerOAuth2AuthorizedClientRepository authorizedClientRepository;
	
	@Bean
	public SecurityWebFilterChain configure(ServerHttpSecurity http) throws Exception {
	    http
	        .oauth2Client().authorizedClientRepository(authorizedClientRepository);
	    return http.build();
	}
}
