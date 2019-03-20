package net.luversof.security.core.context;

import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class ReactiveUserDetailsContextHolder {

	private static final Class<?> USERDETAILS_CONTEXT_KEY = UserDetailsContext.class;

	public static Mono<UserDetailsContext> getContext() {
		return Mono.subscriberContext().filter(c -> c.hasKey(USERDETAILS_CONTEXT_KEY))
				.flatMap(c -> c.<Mono<UserDetailsContext>>get(USERDETAILS_CONTEXT_KEY));
	}
	
	public static Function<Context, Context> clearContext() {
		return context -> context.delete(USERDETAILS_CONTEXT_KEY);
	}
	
	public static Context withUserDetailsContext(Mono<? extends UserDetailsContext> userDetailsContext) {
		return Context.of(USERDETAILS_CONTEXT_KEY, userDetailsContext);
	}
	
	public static Context withUserDetails(UserDetails userDetails) {
		return withUserDetailsContext(Mono.just(new UserDetailsContextImpl(userDetails)));
	}

}
