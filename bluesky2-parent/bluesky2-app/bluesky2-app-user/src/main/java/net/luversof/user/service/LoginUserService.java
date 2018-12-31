package net.luversof.user.service;

import java.util.UUID;

import net.luversof.user.domain.User;
import reactor.core.publisher.Mono;

public interface LoginUserService {
	
	Mono<User> getUser();
	
	/**
	 * 로그인한 유저의 UserId를 반환
	 * @return
	 */
	Mono<UUID> getUserId();
}
