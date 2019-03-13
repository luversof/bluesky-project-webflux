package net.luversof.bookkeeping.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import net.luversof.bookkeeping.domain.Entry;

public interface EntryRepository extends ReactiveMongoRepository<Entry, ObjectId> {
//	Flux<Entry> findByBookkeepingId(ObjectId bookkeepingId);
}
