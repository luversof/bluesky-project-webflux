package net.luversof.bookkeeping.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import net.luversof.bookkeeping.domain.Asset;
import reactor.core.publisher.Flux;

public interface AssetRepository extends ReactiveMongoRepository<Asset, String> {
	
	Flux<Asset> findByUserId(UUID userId);

}