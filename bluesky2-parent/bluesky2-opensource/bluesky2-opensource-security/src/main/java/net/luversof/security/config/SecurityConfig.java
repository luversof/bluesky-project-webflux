package net.luversof.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;

import net.luversof.security.core.userdetails.BlueskyUserDetailsService;
import net.luversof.security.oauth2.client.BlueskyReactiveOAuth2AuthorizedClientService;

@Configuration
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private BlueskyUserDetailsService userDetailsService;
	
	@Autowired
	private ServerOAuth2AuthorizedClientRepository authorizedClientRepository;
	
	@Autowired
	private BlueskyReactiveOAuth2AuthorizedClientService blueskyReactiveOAuth2AuthorizedClientService;
	
	@Bean
	public SecurityWebFilterChain configure(ServerHttpSecurity http) throws Exception {
	    http
//	        .oauth2Client().authorizedClientRepository(authorizedClientRepository);
	    	.csrf().disable()
	    	.oauth2Login().authorizedClientService(blueskyReactiveOAuth2AuthorizedClientService);
	    return http.build();
	}
}
