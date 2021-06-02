package net.luversof.bookkeeping;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.luversof.bookkeeping.constant.AssetType;
import net.luversof.bookkeeping.domain.Bookkeeping;
import net.luversof.bookkeeping.service.BookkeepingService;
import net.luversof.test.GeneralTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class BookkeepingTest extends GeneralTest {

	@Autowired
	private BookkeepingService bookkeepingService;
	
	UUID userId = UUID.fromString("ea14af5b-c2bc-46ea-ab3c-e22dd7b364d1");
	
	
	@Test
	public void t1_create() {
		Mono<Bookkeeping> bookkeepingMono = bookkeepingService.getUserBookkeeping(userId)
				.flatMap(bookkeeping -> {
					if (bookkeeping.getId() == null) {
						return bookkeepingService.createUserBookkeeping(bookkeeping);
					}
					return Mono.just(bookkeeping);
				}).log();
		
		StepVerifier.create(bookkeepingMono).expectNextMatches(bookkeeping -> bookkeeping.getUserId().equals(userId))
				.expectComplete().verify();
	}
	
	@Test
	public void t2_findByUserId() {
		Mono<Bookkeeping> bookkeepingFlux = bookkeepingService.getUserBookkeeping(userId).log();
		
		StepVerifier.create(bookkeepingFlux)
			.expectNextMatches(bookkeeping -> bookkeeping.getUserId().equals(userId))
			.expectComplete()
			.verify();
	}
	
}
