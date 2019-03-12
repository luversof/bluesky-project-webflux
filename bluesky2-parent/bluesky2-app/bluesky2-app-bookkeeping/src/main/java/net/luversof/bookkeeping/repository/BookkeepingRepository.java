package net.luversof.bookkeeping.repository;

import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import net.luversof.bookkeeping.domain.Bookkeeping;
import reactor.core.publisher.Flux;

public interface BookkeepingRepository extends ReactiveMongoRepository<Bookkeeping, ObjectId> {
	
	Flux<Bookkeeping> findByUserId(UUID userId);
	
//	Mono<Bookkeeping> findByAssetListId(ObjectId id);

}
