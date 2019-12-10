package net.luversof.bookkeeping.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import net.luversof.bookkeeping.domain.Bookkeeping;
import reactor.core.publisher.Flux;

@Transactional(readOnly = true)
public interface BookkeepingRepository extends ReactiveCrudRepository<Bookkeeping, Long> {
	
	Flux<Bookkeeping> findByUserId(UUID userId);

}
