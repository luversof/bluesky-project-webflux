package net.luversof.security.core.context;

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
		return mainContext.putAll(reactiveUserDetailsService.findByUsername("").flatMap(userDetails -> {
			return Mono.just(new UserDetailsContextImpl(userDetails));
		}).as(ReactiveUserDetailsContextHolder::withUserDetailsContext));
	}
}
