package net.luversof.bookkeeping.repository;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import net.luversof.bookkeeping.domain.Entry;
import reactor.core.publisher.Flux;

public interface EntryRepository extends ReactiveMongoRepository<Entry, ObjectId> {
	
	Flux<Entry> findByBookkeepingId(ObjectId bookkeepingId);
	
	Flux<Entry> findByAssetId(ObjectId assetId);
	
	Flux<Entry> findByBookkeepingIdAndEntryDateBetween(ObjectId bookkeepingId, LocalDateTime startDate, LocalDateTime endDate);
}
