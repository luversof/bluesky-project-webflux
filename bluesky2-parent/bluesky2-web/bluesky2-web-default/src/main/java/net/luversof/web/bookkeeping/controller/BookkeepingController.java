package net.luversof.web.bookkeeping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.luversof.bookkeeping.domain.Bookkeeping;
import net.luversof.bookkeeping.service.BookkeepingService;
import net.luversof.security.core.context.ReactiveUserDetailsContextHolder;
import net.luversof.security.core.context.UserDetailsContext;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/bookkeeping")
public class BookkeepingController {

	@Autowired
	private BookkeepingService bookkeepingService;
	
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/search/myBookkeeping")
	public Mono<Bookkeeping> findByUserId(Authentication authentication) {
		Mono<SecurityContext> context = ReactiveSecurityContextHolder.getContext();
		Mono<UserDetailsContext> context2 = ReactiveUserDetailsContextHolder.getContext();
		log.debug("context : {}, {}", context, context2);
		return bookkeepingService.findByUserId(null).next();
	}
}
