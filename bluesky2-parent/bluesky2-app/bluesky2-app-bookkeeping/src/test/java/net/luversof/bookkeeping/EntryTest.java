package net.luversof.bookkeeping;

import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import net.luversof.bookkeeping.service.BookkeepingService;
import net.luversof.test.GeneralTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
public class EntryTest extends GeneralTest {
//
//	@Autowired
//	private BookkeepingService bookkeepingService;
//
//	@Autowired
//	private EntryService entryService;
//
//	@Autowired
//	private EntryRepository entryRepository;
//
//	UUID userId = UUID.fromString("ea14af5b-c2bc-46ea-ab3c-e22dd7b364d1");
//
//	ObjectId assetId = new ObjectId("5cade44eef53c92c385ac51f");
//
//	@Test
//	public void t1_addEntry() {
//
//		Mono<Entry> entryMono = bookkeepingService.findByUserId(userId).next().flatMap(bookkeeping -> {
//			log.debug("TEST : {}", bookkeeping);
//			Entry entry = new Entry();
//			entry.setBookkeepingId(bookkeeping.getId());
//			entry.setAssetId(assetId);
//			entry.setEntryDate(LocalDateTime.now());
//			entry.setAmount(208);
//			entry.setMemo("test");
//			return entryService.add(entry);
//		}).log();
//
//		StepVerifier.create(entryMono).expectNextMatches(entry -> entry.getAssetId().equals(assetId)).expectComplete()
//				.verify();
//	}
//	
//	ObjectId bookkeepingId;
//	
//	@Test
//	public void t2_findEntry() {
//		Flux<Entry> entryFlux = bookkeepingService.findByUserId(userId).next().flatMapMany(bookkeeping -> {
//			return Flux.fromIterable(bookkeeping.getAssetList()).flatMap(asset -> {
//				Entry entry = new Entry();
//				entry.setBookkeepingId(bookkeeping.getId());
//				entry.setAssetId(asset.getId());
//				bookkeepingId = entry.getBookkeepingId();
//				return entryService.findByAssetId(entry);
//			});
//		})
//		.flatMap(entry -> {
//			log.debug("entry : {}", entry.getAssetId());
//			return Mono.just(entry);
//		})
//		.log();
//
//		StepVerifier.create(entryFlux)
////			.expectNextMatches(entry -> {
////				log.debug("targetEntry : {}, {}, {}", entry.getAssetId().equals(assetId), entry.getAssetId(), assetId);
////				return entry.getAssetId().equals(assetId);
////			})
//		.expectComplete()
//		.verify();
////		.verifyComplete();
////			.expectComplete()
////			.expectSubscription()
////				.expectComplete().verify();
////		.verifyComplete();
//	}
//
//	@Test
//	public void t3_updateEntry() {
//		Mono<Entry> entryMono = entryRepository.findAll().next().flatMap(entry -> {
//			entry.setAmount(42);
//			entry.setMemo("메모수정");
//			return entryService.update(entry);
//		}).log();
//
//		StepVerifier.create(entryMono).expectNextMatches(entry -> entry.getAssetId().equals(assetId)).expectComplete()
//				.verify();
//	}
//	
//	@Test
//	public void t4_deleteEntry() {
//		Mono<Void> voidMono = entryRepository.findAll().next().flatMap(entry -> {
//			return entryService.delete(userId, entry.getId());
//		});
//		
//		StepVerifier.create(voidMono).verifyComplete();
//	}
//	
//	@Test
//	public void t5_findEntry() {
//		Flux<Entry> entryFlux =  bookkeepingService.findByUserId(userId).next().flatMapMany(bookkeeping -> {
//			log.debug("EEEEEE : {}", bookkeeping);
//			return entryRepository.findByBookkeepingIdAndEntryDateBetween(bookkeeping.getId(), LocalDateTime.now().minusYears(10), LocalDateTime.now());
//		}).log();
//		
//		StepVerifier.create(entryFlux).expectNextMatches(entry -> {
//			log.debug("test!!!! : {}", entry);
//			return true;
//		}).expectComplete().verify();
//	}
}
