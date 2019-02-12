//package net.luversof.security.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
//
//import lombok.SneakyThrows;
//import net.luversof.security.oauth2.client.BlueskyReactiveOAuth2AuthorizedClientService;
//
//@Configuration
//public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
//	@Autowired
//	private UserDetailsService userDetailsService;
//	
//	@Autowired
//	private BlueskyReactiveOAuth2AuthorizedClientService blueskyOAuth2AuthorizedClientService;
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//		super.configure(auth);
//	}
//
//
//
//	@Override
//	@SneakyThrows
//	protected void configure(HttpSecurity http) {
//		SimpleUrlLogoutSuccessHandler logoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();
//		logoutSuccessHandler.setUseReferer(true);
//		
//		SimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler = new SimpleUrlAuthenticationSuccessHandler();
//		authenticationSuccessHandler.setUseReferer(true);
//		
//		http
//			.headers().frameOptions().sameOrigin().and()
//			.authorizeRequests()
//				.antMatchers("/test/**").permitAll()
////				.anyRequest().authenticated()
//			.and()
////			.addFilterBefore(new OAuth2ClientContextFilter(), UsernamePasswordAuthenticationFilter.class)
//			.exceptionHandling().accessDeniedPage("/error/accessDenied").and()
//			.logout().logoutSuccessHandler(logoutSuccessHandler).and()
//			.formLogin().loginPage("/login").successHandler(authenticationSuccessHandler).and()
//			.rememberMe().and()
//			.oauth2Login().successHandler(authenticationSuccessHandler)
//				.authorizedClientService(blueskyOAuth2AuthorizedClientService).and()
////			.oauth2Client().authorizedClientService(blueskyOAuth2AuthorizedClientService).and()
////			.csrf().and()
//			.csrf().disable()
//            .httpBasic().and()
//            ;
//	}
//}
