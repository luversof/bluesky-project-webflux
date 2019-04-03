package net.luversof.bookkeeping;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import net.luversof.bookkeeping.domain.Bookkeeping;
import net.luversof.bookkeeping.domain.Entry;
import net.luversof.bookkeeping.repository.EntryRepository;
import net.luversof.bookkeeping.service.BookkeepingService;
import net.luversof.bookkeeping.service.EntryService;
import net.luversof.test.GeneralTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EntryTest extends GeneralTest {

	@Autowired
	private BookkeepingService bookkeepingService;

	@Autowired
	private EntryService entryService;

	@Autowired
	private EntryRepository entryRepository;

	UUID userId = UUID.fromString("ea14af5b-c2bc-46ea-ab3c-e22dd7b364d1");

	ObjectId assetId = new ObjectId("5c86bb6eef53c9446c916876");

	@Test
	public void t1_addEntry() {

		Mono<Entry> entryMono = bookkeepingService.findByUserId(userId).next().flatMap(bookkeeping -> {
			Entry entry = new Entry();
			entry.setBookkeepingId(bookkeeping.getId());
			entry.setAssetId(assetId);
			entry.setEntryDate(LocalDateTime.now());
			entry.setAmount(208);
			entry.setMemo("test");
			return entryService.add(entry);
		}).log();

		StepVerifier.create(entryMono).expectNextMatches(entry -> entry.getAssetId().equals(assetId)).expectComplete()
				.verify();
	}
	
	@Test
	public void t2_findEntry() {
		Flux<Entry> entryFlux = bookkeepingService.findByUserId(userId).next().flatMapMany(bookkeeping -> {
			Entry entry = new Entry();
			entry.setBookkeepingId(bookkeeping.getId());
			entry.setAssetId(assetId);
			return entryService.findByAssetId(entry);
		}).log();

		StepVerifier.create(entryFlux).expectNextMatches(entry -> entry.getAssetId().equals(assetId)).expectComplete()
				.verify();
	}

	@Test
	public void t3_updateEntry() {
		Mono<Entry> entryMono = entryRepository.findAll().next().flatMap(entry -> {
			entry.setAmount(42);
			entry.setMemo("메모수정");
			return entryService.update(entry);
		}).log();

		StepVerifier.create(entryMono).expectNextMatches(entry -> entry.getAssetId().equals(assetId)).expectComplete()
				.verify();
	}
	
	@Test
	public void t4_deleteEntry() {
		Mono<Void> voidMono = entryRepository.findAll().next().flatMap(entry -> {
			return entryService.delete(entry);
		});
		
		StepVerifier.create(voidMono).verifyComplete();
	}
}
