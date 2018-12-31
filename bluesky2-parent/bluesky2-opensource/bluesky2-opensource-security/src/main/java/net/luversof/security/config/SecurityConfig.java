package net.luversof.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityWebFilterChain configure(ServerHttpSecurity http) throws Exception {
	    http
	        .oauth2Client();
	    return http.build();
	}
}
