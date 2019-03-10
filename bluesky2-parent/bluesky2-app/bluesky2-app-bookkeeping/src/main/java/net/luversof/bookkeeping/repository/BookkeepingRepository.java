package net.luversof.bookkeeping.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import net.luversof.bookkeeping.domain.Bookkeeping;
import reactor.core.publisher.Mono;

public interface BookkeepingRepository extends ReactiveMongoRepository<Bookkeeping, String> {
	
	Mono<Bookkeeping> findByUserId(UUID userId);

}
