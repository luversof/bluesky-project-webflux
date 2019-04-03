package net.luversof.bookkeeping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.luversof.bookkeeping.domain.Asset;
import net.luversof.bookkeeping.domain.Entry;
import net.luversof.bookkeeping.repository.EntryRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EntryService {
	
	@Autowired
	private EntryRepository entryRepository;
	
	@Autowired
	private BookkeepingService bookkeepingService;
	
	public Flux<Entry> findByAssetId(Entry entry) {
		return bookkeepingService.findById(entry.getBookkeepingId()).flatMap(bookkeeping -> {
			bookkeeping.getAssetList().stream()
				.filter(x -> x.getId().equals(entry.getAssetId()))
				.findAny().orElseThrow(() -> new RuntimeException("NOT_EXIST_ASSET"));
			return Mono.just(bookkeeping);
		}).flatMapMany(bookkeeping -> {
			return entryRepository.findAll();
		});
	}
	
	public Mono<Entry> add(Entry entry) {
		return bookkeepingService.findById(entry.getBookkeepingId()).flatMap(bookkeeping -> {
			Asset asset = bookkeeping.getAssetList().stream()
				.filter(x -> x.getId().equals(entry.getAssetId()))
				.findAny().orElseThrow(() -> new RuntimeException("NOT_EXIST_ASSET"));
			return entryRepository.save(entry).flatMap(targetEntry -> {
				asset.setAmount(asset.getAmount() + targetEntry.getAmount());
				return bookkeepingService.save(bookkeeping).thenReturn(targetEntry);
			});
		});
	}
	
	public Mono<Entry> update(Entry entry) {
		return bookkeepingService.findById(entry.getBookkeepingId()).flatMap(bookkeeping -> {
			Asset asset = bookkeeping.getAssetList().stream()
				.filter(x -> x.getId().equals(entry.getAssetId()))
				.findAny().orElseThrow(() -> new RuntimeException("NOT_EXIST_ASSET"));
			return entryRepository.findById(entry.getId()).flatMap(targetEntry -> {
				// 기존 저장된 값과 변경된 값의 차이만큼 업데이트
				long diffAmount = entry.getAmount() - targetEntry.getAmount();
				
				targetEntry.setAmount(entry.getAmount());
				targetEntry.setEntryDate(entry.getEntryDate());
				targetEntry.setMemo(entry.getMemo());
				
				return entryRepository.save(targetEntry).flatMap(resultEntry -> {
					asset.setAmount(asset.getAmount() + diffAmount);
					return bookkeepingService.save(bookkeeping).thenReturn(resultEntry);
				});
				
			});
		});
	}
	
	public Mono<Void> delete(Entry entry) {
		return bookkeepingService.findById(entry.getBookkeepingId()).flatMap(bookkeeping -> {
			bookkeeping.getAssetList().stream()
					.filter(x -> x.getId().equals(entry.getAssetId()))
					.findAny().orElseThrow(() -> new RuntimeException("NOT_EXIST_ASSET"));
			return entryRepository.deleteById(entry.getId());
		});
	}
	
}
