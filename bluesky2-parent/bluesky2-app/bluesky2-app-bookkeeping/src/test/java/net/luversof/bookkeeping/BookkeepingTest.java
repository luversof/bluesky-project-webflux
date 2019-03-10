package net.luversof.bookkeeping;

import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import net.luversof.bookkeeping.domain.Bookkeeping;
import net.luversof.bookkeeping.repository.BookkeepingRepository;
import net.luversof.test.GeneralTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
public class BookkeepingTest extends GeneralTest {

	@Autowired
	private BookkeepingRepository bookkeepingRepository;
	
	
	@Test
	public void save() {
		UUID userId = UUID.fromString("ea14af5b-c2bc-46ea-ab3c-e22dd7b364d1");
		log.debug("Test, {}", userId);
		Mono<Bookkeeping> bookkeepingMono = bookkeepingRepository.findByUserId(userId)
			.switchIfEmpty(Mono.just(new Bookkeeping()))
			.flatMap(bookkeeping -> {
				if (bookkeeping.getId() == null) {
					Bookkeeping savedBookkeeping = new Bookkeeping();
					savedBookkeeping.setUserId(userId);
					return bookkeepingRepository.save(savedBookkeeping);
				}
				return Mono.just(bookkeeping);
			});
		
		StepVerifier.create(bookkeepingMono)
			.expectNextMatches(bookkeeping -> {
				return bookkeeping.getUserId().equals(userId);
			})
			.expectComplete()
			.verify();
	}
}
