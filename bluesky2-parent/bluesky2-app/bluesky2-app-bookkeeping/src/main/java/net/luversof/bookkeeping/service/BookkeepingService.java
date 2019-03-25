package net.luversof.bookkeeping.service;

import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.luversof.bookkeeping.constant.AssetType;
import net.luversof.bookkeeping.domain.Asset;
import net.luversof.bookkeeping.domain.Bookkeeping;
import net.luversof.bookkeeping.repository.BookkeepingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookkeepingService {
	
	@Autowired
	private BookkeepingRepository bookkeepingRepository;
	
	public Mono<Bookkeeping> findById(ObjectId id) {
		return bookkeepingRepository.findById(id);
	}
	
	public Flux<Bookkeeping> findByUserId(UUID userId) {
		//return bookkeepingRepository.findByUserId(userId);
		return bookkeepingRepository.findAll();
	}
	
	/**
	 * 기본 가계부 생성
	 * 기본 asset 을 추가 처리함
	 * 도메인을 넘겨 받아 생성하는 형태보단 UUID userId만 넘겨 받고 기본 설정은 여기서 관리하는 것이 좋을 것으로 판단함.
	 * @param bookkeeping
	 * @return
	 */
	public Mono<Bookkeeping> create(UUID userId) {
		return bookkeepingRepository.findByUserId(userId).switchIfEmpty(Mono.just(new Bookkeeping())).next()
			.flatMap(bookkeeping -> {
				if (bookkeeping.getId() == null) {
					bookkeeping.setUserId(userId);
					bookkeeping.getAssetList().add(new Asset(new ObjectId(), AssetType.WALLET, "지갑"));
					return bookkeepingRepository.save(bookkeeping);
				}
				return Mono.just(bookkeeping);
			});
	}
	
	public Mono<Bookkeeping> addAsset(ObjectId id, Asset asset) {
		return bookkeepingRepository.findById(id)
			.flatMap(bookkeeping -> {
				if (asset.getId() == null) {
					asset.setId(new ObjectId());
				}
				bookkeeping.getAssetList().add(asset);
				return bookkeepingRepository.save(bookkeeping);
			});
	}
	
	/**
	 * asset 정보 변경
	 * @param id
	 * @param asset
	 * @return
	 */
	public Mono<Bookkeeping> updateAsset(ObjectId id, Asset asset) {
		return bookkeepingRepository.findById(id)
				.flatMap(bookkeeping -> {
					for (int i = 0 ; i < bookkeeping.getAssetList().size(); i++) {
						if (bookkeeping.getAssetList().get(i).getId().equals(asset.getId())) {
							bookkeeping.getAssetList().set(i, asset);
							return bookkeepingRepository.save(bookkeeping);
						}
					}
					throw new RuntimeException("NOT_EXIST_ASSET");
				});
	}
	
	Mono<Bookkeeping> save(Bookkeeping bookkeeping) {
		return bookkeepingRepository.save(bookkeeping);
	}
	
	public Mono<Bookkeeping> deleteAsset(ObjectId id, ObjectId assetId) {
		return bookkeepingRepository.findById(id)
				.flatMap(bookkeeping -> {
					for (int i = 0 ; i < bookkeeping.getAssetList().size(); i++) {
						if (bookkeeping.getAssetList().get(i).getId().equals(assetId)) {
							bookkeeping.getAssetList().remove(i);
							return bookkeepingRepository.save(bookkeeping);
						}
					}
					throw new RuntimeException("NOT_EXIST_ASSET");
				});
	}
	
}
