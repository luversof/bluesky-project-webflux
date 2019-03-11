package net.luversof.bookkeeping;

import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import net.luversof.bookkeeping.domain.Bookkeeping;
import net.luversof.bookkeeping.service.BookkeepingService;
import net.luversof.test.GeneralTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
public class BookkeepingTest extends GeneralTest {

	@Autowired
	private BookkeepingService bookkeepingService;
	
	UUID userId = UUID.fromString("ea14af5b-c2bc-46ea-ab3c-e22dd7b364d1");
	
	@Test
	public void create() {
		Mono<Bookkeeping> bookkeepingMono = bookkeepingService.findByUserId(userId).switchIfEmpty(Mono.just(new Bookkeeping())).next()
			.flatMap(bookkeeping -> {
				if (bookkeeping.getId() == null) {
					return bookkeepingService.create(userId);
				}
				return Mono.just(bookkeeping);
			}).log();
		
		StepVerifier.create(bookkeepingMono)
			.expectNextMatches(bookkeeping -> bookkeeping.getUserId().equals(userId))
			.expectComplete()
			.verify();
	}
	
	@Test
	public void findByUserId() {
		Flux<Bookkeeping> bookkeepingFlux = bookkeepingService.findByUserId(userId).log();
		
		StepVerifier.create(bookkeepingFlux)
			.expectNextMatches(bookkeeping -> bookkeeping.getUserId().equals(userId))
			.expectComplete()
			.verify();
	}
	
	@Test
	public void addAsset() {
		Flux<Bookkeeping> bookkeepingFlux = bookkeepingService.findByUserId(userId).log();
		
		
		StepVerifier.create(bookkeepingFlux)
			.expectNextMatches(bookkeeping -> {
				log.debug("bookeeping : {}, {}", bookkeeping, bookkeeping.getAssetList());
				return bookkeeping.getUserId().equals(userId);
			})
			.expectComplete()
			.verify();
		
		
		log.debug("TEst : {}", bookkeepingFlux.blockFirst());
	}
}
