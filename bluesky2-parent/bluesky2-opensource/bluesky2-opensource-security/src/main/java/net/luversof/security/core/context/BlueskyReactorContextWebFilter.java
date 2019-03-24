package net.luversof.security.core.context;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import lombok.AllArgsConstructor;
import net.luversof.security.core.userdetails.BlueskyUser;
import net.luversof.security.core.userdetails.BlueskyUserDetailsService;
import net.luversof.user.domain.UserType;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

@AllArgsConstructor
public class BlueskyReactorContextWebFilter implements WebFilter {
	
	private final BlueskyUserDetailsService blueskyUserDetailsService;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		return chain.filter(exchange).subscriberContext(c -> c.hasKey(UserDetailsContext.class) ? c : withUserDetailsContext(c, exchange));
	}

	private Context withUserDetailsContext(Context mainContext, ServerWebExchange exchange) {
		return mainContext.putAll(ReactiveSecurityContextHolder.getContext().flatMap(securityContext -> {
			Authentication authentication = securityContext.getAuthentication();
			if (authentication.getPrincipal() instanceof BlueskyUser) {
				return Mono.just((BlueskyUser) authentication.getPrincipal());
			}
			if (authentication instanceof OAuth2AuthenticationToken) {
				OAuth2User principal = ((OAuth2AuthenticationToken) authentication).getPrincipal();
				return blueskyUserDetailsService.findByExternalIdAndUserType(String.valueOf(principal.getAttributes().get("id")), UserType.findByName(((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId()));
			}
			return Mono.empty();	// 이 경우는 나중에 필요할 떄 작업
		}).flatMap(userDetails -> {
			UserDetailsContext userDetailsContext = new UserDetailsContextImpl(userDetails);
			return Mono.just(userDetailsContext);
		}).as(ReactiveUserDetailsContextHolder::withUserDetailsContext));

	}
}
