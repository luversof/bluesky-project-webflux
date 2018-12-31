package net.luversof.user.service;

import java.util.UUID;

import net.luversof.user.domain.User;
import reactor.core.publisher.Mono;

/**
 * 실제 연동되는 loginUserService가 없을 경우 사용되는 서비스
 * @author bluesky
 *
 */
public class EmptyLoginUserService implements LoginUserService {

	@Override
	public Mono<User> getUser() {
		return Mono.empty();
	}

	@Override
	public Mono<UUID> getUserId() {
		return Mono.just(UUID.randomUUID());
	}

}
