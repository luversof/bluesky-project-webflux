package net.luversof.web.bookkeeping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.luversof.bookkeeping.constant.AssetType;
import net.luversof.bookkeeping.domain.Bookkeeping;
import net.luversof.bookkeeping.service.BookkeepingService;
import net.luversof.security.core.userdetails.BlueskyUser;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/bookkeeping")
public class BookkeepingController {

	@Autowired
	private BookkeepingService bookkeepingService;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/search/myBookkeeping")
	public Mono<Bookkeeping> findByUserId(BlueskyUser user) {
		return bookkeepingService.findByUserId(user.getId()).next();
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping
	public Mono<Bookkeeping> save(BlueskyUser user) {
		return bookkeepingService.create(user.getId());
	}
	
	@GetMapping("/assetTypes")
	public Flux<AssetType> assetTypes() {
		return Flux.just(AssetType.values());
	}
}
