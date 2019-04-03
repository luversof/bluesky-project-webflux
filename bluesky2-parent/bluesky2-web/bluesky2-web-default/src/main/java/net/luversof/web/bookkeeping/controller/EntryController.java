package net.luversof.web.bookkeeping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.luversof.bookkeeping.domain.Entry;
import net.luversof.bookkeeping.service.EntryService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/bookkeeping/")
public class EntryController {

	@Autowired
	private EntryService entryService;
	
	@PostMapping("/entry")
	public Mono<Entry> addEntry(Entry entry) {
		return entryService.add(entry);
	}
}
