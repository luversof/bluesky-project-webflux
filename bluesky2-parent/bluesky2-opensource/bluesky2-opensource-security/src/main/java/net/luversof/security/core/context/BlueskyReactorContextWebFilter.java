package net.luversof.security.core.context;

import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

@AllArgsConstructor
public class BlueskyReactorContextWebFilter implements WebFilter {
	
	private final ReactiveUserDetailsService reactiveUserDetailsService;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		return chain.filter(exchange).subscriberContext(c -> c.hasKey(UserDetailsContext.class) ? c : withUserDetailsContext(c, exchange));
	}

	private Context withUserDetailsContext(Context mainContext, ServerWebExchange exchange) {
		return mainContext.putAll(ReactiveSecurityContextHolder.getContext().flatMap(securityContext -> {
			return reactiveUserDetailsService.findByUsername("");
		}).flatMap(userDetails -> {
			UserDetailsContext userDetailsContext = new UserDetailsContextImpl(userDetails);
			return Mono.just(userDetailsContext);
		}).as(ReactiveUserDetailsContextHolder::withUserDetailsContext));
		
//		return mainContext.putAll(ReactiveSecurityContextHolder.getContext().flatMap(securityContext -> {
//			return reactiveUserDetailsService.findByUsername("");
//		}).as(ReactiveUserDetailsContextHolder::withUserDetails));
		
//		return mainContext.putAll(exchange.getSession().map(WebSession::getAttributes).flatMap(attrs -> {
//			SecurityContext context = (SecurityContext) attrs.get("");
//			return reactiveUserDetailsService.findByUsername("").flatMap(userDetails -> {
//				return Mono.just(new UserDetailsContextImpl(userDetails));
//			});
//		}).as(ReactiveUserDetailsContextHolder::withUserDetailsContext));

	}
}
