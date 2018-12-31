package net.luversof.user.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import net.luversof.user.domain.User;
import net.luversof.user.domain.UserType;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

	Mono<User> findByUsername(String username);
	
	Mono<User> findByExternalIdAndUserType(String externalId, UserType userType);
}
