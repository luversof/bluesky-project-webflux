package net.luversof.bookkeeping.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.luversof.bookkeeping.constant.AssetType;
import net.luversof.bookkeeping.domain.Asset;
import net.luversof.bookkeeping.repository.AssetRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AssetService {

	@Autowired
	private AssetRepository assetRepository;
	
	public Flux<Asset> findByUserId(UUID userId) {
		return assetRepository.findByUserId(userId);
	}
	
	/**
	 * 기본 asset 생성
	 * @param userId
	 * @return
	 */
	public Flux<Asset> create(UUID userId) {
		return findByUserId(userId).switchIfEmpty(Flux.just(new Asset(userId, AssetType.WALLET, "지갑")))
			.flatMap(asset -> asset.getId() == null ? assetRepository.save(asset) : Flux.just(asset));
	}
	
	/**
	 * asset 추가
	 * 자세한 체크 사항은 추후 구현
	 * @param asset
	 * @return
	 */
	public Mono<Asset> add(Asset asset) {
		return assetRepository.save(asset);
	}
}
