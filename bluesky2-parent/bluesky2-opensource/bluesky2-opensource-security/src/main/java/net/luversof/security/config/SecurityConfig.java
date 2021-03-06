//package net.luversof.security.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter.Mode;
//
//import net.luversof.security.core.context.BlueskyReactorContextWebFilter;
//import net.luversof.security.core.userdetails.BlueskyUserDetailsService;
//import net.luversof.security.oauth2.client.BlueskyReactiveOAuth2AuthorizedClientService;
//
//@Configuration
//@EnableWebFluxSecurity
////@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableReactiveMethodSecurity
//public class SecurityConfig {
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Autowired
//	private BlueskyReactiveOAuth2AuthorizedClientService blueskyReactiveOAuth2AuthorizedClientService;
//	
//	@Autowired
//	private BlueskyUserDetailsService blueskyUserDetailsService;
//	
//	@Bean
//	public SecurityWebFilterChain configure(ServerHttpSecurity http) throws Exception {
//		SimpleUrlLogoutSuccessHandler logoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();
//		logoutSuccessHandler.setUseReferer(true);
//		
//	    http
//	    	.headers().frameOptions().mode(Mode.SAMEORIGIN).and()
//	    	.addFilterAt(new BlueskyReactorContextWebFilter(blueskyUserDetailsService), SecurityWebFiltersOrder.SERVER_REQUEST_CACHE)
//	    	.authorizeExchange()
//	    		.anyExchange().permitAll()
//	    		.and()
////	    	 .exceptionHandling().accessDeniedHandler(accessDeniedHandler) // 이거 어떻게 바뀌었을까?
////	    	.logout().logoutSuccessHandler(logoutSuccessHandler) // 5.0 이후
//	    	.formLogin().and()
////	        .oauth2Client().authorizedClientRepository(authorizedClientRepository);
//	    	.oauth2Login().authorizedClientService(blueskyReactiveOAuth2AuthorizedClientService).and()
//	    	.csrf().disable()
//	    	.httpBasic().and();
//	    return http.build();
//	}
//}
