package net.luversof.web.reactive.result.method;

import org.springframework.core.MethodParameter;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolverSupport;
import org.springframework.web.server.ServerWebExchange;

import net.luversof.security.core.context.ReactiveUserDetailsContextHolder;
import reactor.core.publisher.Mono;

public class UserDetailsHandlerMethodArgumentResolver extends HandlerMethodArgumentResolverSupport {

	public UserDetailsHandlerMethodArgumentResolver(ReactiveAdapterRegistry adapterRegistry) {
		super(adapterRegistry);
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return checkParameterType(parameter, UserDetails.class::isAssignableFrom);
	}

	@Override
	public Mono<Object> resolveArgument(MethodParameter parameter, BindingContext bindingContext, ServerWebExchange exchange) {
		return ReactiveUserDetailsContextHolder.getContext().flatMap(context -> {
			return Mono.just(context.getUserDetails());
		});
	}

}
