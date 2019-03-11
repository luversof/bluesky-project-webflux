package net.luversof.bookkeeping.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.luversof.bookkeeping.domain.Asset;
import net.luversof.bookkeeping.domain.Bookkeeping;
import net.luversof.bookkeeping.repository.BookkeepingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookkeepingService {
	
	@Autowired
	private BookkeepingRepository bookkeepingRepository;
	
	@Autowired
	private AssetService assetService;
	
	public Flux<Bookkeeping> findByUserId(UUID userId) {
		return bookkeepingRepository.findByUserId(userId);
	}
	
	/**
	 * 기본 가계부 생성
	 * 기본 asset 을 추가 처리함
	 * 도메인을 넘겨 받아 생성하는 형태보단 UUID userId만 넘겨 받고 기본 설정은 여기서 관리하는 것이 좋을 것으로 판단함.
	 * @param bookkeeping
	 * @return
	 */
	public Mono<Bookkeeping> create(UUID userId) {
		return Mono.just(new Bookkeeping())
			.flatMap(bookkeeping -> {
				if (bookkeeping.getId() == null) {
					return assetService.create(userId).flatMap(asset -> {
						bookkeeping.getAssetList().add(asset);
						return Flux.just(bookkeeping);
					}).then(Mono.just(bookkeeping));
				}
				return Mono.just(bookkeeping);
			})
			.flatMap(bookkeeping -> {
				if (bookkeeping.getId() == null) {
					bookkeeping.setUserId(userId);
					return bookkeepingRepository.save(bookkeeping);
				}
				return Mono.just(bookkeeping);
			});
	}
	
	public Mono<Bookkeeping> addAsset(String id, Asset asset) {
		return bookkeepingRepository.findById(id)
			.flatMap(bookkeeping -> {
				assetService.add(asset).flatMap(targetAsset -> {
					bookkeeping.getAssetList().add(targetAsset);
					return Mono.just(targetAsset);
				});
				return Mono.just(bookkeeping);
			})
			.flatMap(bookkeeping -> {
				bookkeepingRepository.save(bookkeeping);
				return Mono.just(bookkeeping);
			});
	}
	
}
