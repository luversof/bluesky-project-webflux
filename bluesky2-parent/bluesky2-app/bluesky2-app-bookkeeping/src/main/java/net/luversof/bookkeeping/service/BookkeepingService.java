package net.luversof.bookkeeping.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.luversof.bookkeeping.domain.Bookkeeping;
import net.luversof.bookkeeping.repository.BookkeepingRepository;
import reactor.core.publisher.Mono;

@Service
public class BookkeepingService {
	
	@Autowired
	private BookkeepingRepository bookkeepingRepository;

	Mono<Bookkeeping> findByUserId(UUID userId) {
		return bookkeepingRepository.findByUserId(userId);
	}
	
	/**
	 * 기본 가계부 생성
	 * 
	 * @param bookkeeping
	 * @return
	 */
	Mono<Bookkeeping> create(Bookkeeping bookkeeping) {
		return bookkeepingRepository.save(bookkeeping);
	}
}
