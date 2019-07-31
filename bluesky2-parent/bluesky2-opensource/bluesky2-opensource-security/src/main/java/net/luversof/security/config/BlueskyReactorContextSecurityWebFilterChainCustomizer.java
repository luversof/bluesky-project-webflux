package net.luversof.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.stereotype.Component;

import net.luversof.boot.autoconfigure.security.reactive.SecurityWebFilterChainCustomizer;
import net.luversof.security.core.context.BlueskyReactorContextWebFilter;
import net.luversof.security.core.userdetails.BlueskyUserDetailsService;

@Component
public class BlueskyReactorContextSecurityWebFilterChainCustomizer implements SecurityWebFilterChainCustomizer {
	
	@Autowired
	private BlueskyUserDetailsService blueskyUserDetailsService;

	@Override
	public void postConfigure(ServerHttpSecurity http) {
		http.addFilterAt(new BlueskyReactorContextWebFilter(blueskyUserDetailsService), SecurityWebFiltersOrder.SERVER_REQUEST_CACHE);
	}
	
}
