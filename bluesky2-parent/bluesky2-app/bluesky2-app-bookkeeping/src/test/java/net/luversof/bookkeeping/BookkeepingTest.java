package net.luversof.bookkeeping;

import java.util.UUID;

import org.bson.types.ObjectId;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import net.luversof.bookkeeping.constant.AssetType;
import net.luversof.bookkeeping.domain.Asset;
import net.luversof.bookkeeping.domain.Bookkeeping;
import net.luversof.bookkeeping.service.BookkeepingService;
import net.luversof.test.GeneralTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookkeepingTest extends GeneralTest {

	@Autowired
	private BookkeepingService bookkeepingService;
	
	UUID userId = UUID.fromString("ea14af5b-c2bc-46ea-ab3c-e22dd7b364d1");
	
	ObjectId assetId = new ObjectId("5c86bb6eef53c9446c916876");
	
	@Test
	public void t1_create() {
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
	public void t2_findByUserId() {
		Flux<Bookkeeping> bookkeepingFlux = bookkeepingService.findByUserId(userId).log();
		
		StepVerifier.create(bookkeepingFlux)
			.expectNextMatches(bookkeeping -> bookkeeping.getUserId().equals(userId))
			.expectComplete()
			.verify();
	}
	
	@Test
	public void t3_addAsset() {
		
		Flux<Bookkeeping> bookkeepingFlux = bookkeepingService.findByUserId(userId).flatMap(bookkeeping -> {
			Asset asset = new Asset();
			asset.setId(assetId);
			asset.setAssetType(AssetType.WALLET);
			asset.setName("지갑");
			return bookkeepingService.addAsset(bookkeeping.getId(), asset);
		});
		
		StepVerifier.create(bookkeepingFlux)
			.expectNextMatches(bookkeeping -> {
				log.debug("bookeeping : {}", bookkeeping);
				return bookkeeping.getUserId().equals(userId);
			})
			.expectComplete()
			.verify();
	}
	
	@Test
	public void t4_updateAsset() {
		
		Flux<Bookkeeping> bookkeepingFlux = bookkeepingService.findByUserId(userId).flatMap(bookkeeping -> {
			Asset asset = new Asset();
			asset.setId(assetId);
			asset.setAssetType(AssetType.WALLET);
			asset.setName("지갑이름 변경");
			return bookkeepingService.updateAsset(bookkeeping.getId(), asset);
		});
		
		StepVerifier.create(bookkeepingFlux)
		.expectNextMatches(bookkeeping -> {
			log.debug("bookeeping : {}", bookkeeping);
			return true;
		})
		.expectComplete()
		.verify();
	}
	
	@Test
	public void t5_deleteAsset() {
		
		Flux<Bookkeeping> bookkeepingFlux = bookkeepingService.findByUserId(userId).flatMap(bookkeeping -> {
			return bookkeepingService.deleteAsset(bookkeeping.getId(), assetId);
		});
		
		StepVerifier.create(bookkeepingFlux)
		.expectNextMatches(bookkeeping -> {
			log.debug("bookeeping : {}", bookkeeping);
			return true;
		})
		.expectComplete()
		.verify();
	}
}
