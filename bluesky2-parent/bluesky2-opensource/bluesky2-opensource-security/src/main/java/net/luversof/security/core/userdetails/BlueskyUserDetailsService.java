package net.luversof.security.core.userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import net.luversof.user.domain.UserType;
import net.luversof.user.service.UserService;
import reactor.core.publisher.Mono;

@Service
public class BlueskyUserDetailsService implements ReactiveUserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public Mono<UserDetails> findByUsername(String username) {
		return userService.findByUsername(username).flatMap(user -> {
			return Mono.just(new BlueskyUser(user));
		});
	}
	
	public Mono<UserDetails> findByExternalIdAndUserType(String externalId, UserType userType) {
		return userService.findByExternalIdAndUserType(externalId, userType).flatMap(user -> {
			return Mono.just(new BlueskyUser(user));
		});
	}
}
