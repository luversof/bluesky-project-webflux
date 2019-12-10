package net.luversof.bookkeeping.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.luversof.bookkeeping.constant.BookkeepingConstants;
import net.luversof.bookkeeping.constant.BookkeepingErrorCode;
import net.luversof.bookkeeping.constant.AssetType;
import net.luversof.bookkeeping.domain.Bookkeeping;
import net.luversof.bookkeeping.repository.BookkeepingRepository;
import net.luversof.boot.exception.BlueskyException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookkeepingService {
	
	@Autowired
	private BookkeepingRepository bookkeepingRepository;
	
	/**
	 * 가계부 생성시 아래 default 데이터 생성
	 * 기본 자산 (asset)
	 * 기본 기록 그룹 (entryGroup)
	 * @param bookkeeping
	 * @return
	 */
	@Transactional(BookkeepingConstants.BOOKKEEPING_TRANSACTIONMANAGER)
	public Mono<Bookkeeping> createUserBookkeeping(Bookkeeping bookkeeping) {
		if (bookkeeping.getUserId() == null) {
			throw new BlueskyException(BookkeepingErrorCode.NOT_EXIST_USER_ID);
		}

		return bookkeepingRepository.save(bookkeeping);
//		List<AssetGroup> assetGroupList = assetGroupService.initialDataSave(bookkeeping);
//		assetService.initialDataSave(bookkeeping, assetGroupList);
//		entryGroupService.initialDataSave(bookkeeping);
//		return bookkeeping;
	}

	/**
	 * userId 기반으로 bookkeeping 정보를 조회한다.
	 * N개 이상 있는 경우는 첫번째 반환 처리함
	 * 
	 * @param bookkeeping
	 * @return
	 */
	public Mono<Bookkeeping> getUserBookkeeping(UUID userId) {
		if (userId == null) {
			throw new BlueskyException(BookkeepingErrorCode.NOT_EXIST_USER_ID);
		}
		return bookkeepingRepository.findByUserId(userId).switchIfEmpty(Mono.just(new Bookkeeping())).next().flatMap(bookkeeping -> {
			return Mono.just(bookkeeping);
		});
	}

	/**
	 * 유저의 가계부 변경
	 *
	 * @param bookkeeping
	 * @return
	 */
	public Mono<Bookkeeping> updateUserBookkeeping(Bookkeeping bookkeeping) {
		return getUserBookkeeping(bookkeeping.getUserId()).switchIfEmpty(Mono.error(new BlueskyException(BookkeepingErrorCode.NOT_EXIST_BOOKKEEPING))).flatMap(targetBookkeeping -> {
			if (bookkeeping.getBaseDate() > 0) {
				targetBookkeeping.setBaseDate(bookkeeping.getBaseDate());
			}
			
			if (bookkeeping.getName() != null) {
				targetBookkeeping.setName(bookkeeping.getName());
			}
			
			return bookkeepingRepository.save(targetBookkeeping);
		});
	}

	/**
	 * 완전 삭제의 경우 관련한 데이터를 모두 삭제 처리
	 * @param bookkeeping
	 */
	@Transactional(BookkeepingConstants.BOOKKEEPING_TRANSACTIONMANAGER)
	public Mono<Void> deleteUserBookkeeping(Bookkeeping bookkeeping) {
		return getUserBookkeeping(bookkeeping.getUserId()).switchIfEmpty(Mono.error(new BlueskyException(BookkeepingErrorCode.NOT_EXIST_BOOKKEEPING))).flatMap(targetBookkeeping -> {
//			entryService.deleteByBookkeepingId(targetBookkeeping.getId());
//			entryGroupService.deleteBybookkeepingId(targetBookkeeping.getId());
//			assetService.deleteBybookkeepingId(targetBookkeeping.getId());
//			assetGroupService.deleteByBookkeepingId(targetBookkeeping.getId());
			return bookkeepingRepository.delete(targetBookkeeping);
		});
	}
	
}
