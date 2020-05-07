//package net.luversof.web.bookkeeping.controller;
//
//import org.bson.types.ObjectId;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import net.luversof.bookkeeping.domain.Entry;
//import net.luversof.bookkeeping.service.BookkeepingService;
//import net.luversof.bookkeeping.service.EntryService;
//import net.luversof.security.core.userdetails.BlueskyUser;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@RestController
//@RequestMapping("/api/bookkeeping/entry")
//@PreAuthorize("hasRole('ROLE_USER')")
//public class EntryController {
//
//	
//	@Autowired
//	private BookkeepingService bookkeepingService;
//	
//	@Autowired
//	private EntryService entryService;
//	
//	@GetMapping("/search/myEntryList")
//	public Flux<Entry> findByAssetId(BlueskyUser user) {
//		return bookkeepingService.findByUserId(user.getId()).next().flatMapMany(bookkeeping -> {
//			return entryService.findByBookkeepingId(bookkeeping.getId());
//		});
//	}
//	
//	@GetMapping("/search/findByAssetId")
//	public Flux<Entry> findByAssetId(@RequestBody Entry entry) {
//		return entryService.findByAssetId(entry);
//	}
//	
//	@PostMapping
//	public Mono<Entry> addEntry(@RequestBody Entry entry) {
//		return entryService.add(entry);
//	}
//	
//	@PutMapping
//	public Mono<Entry> updateEntry(@RequestBody Entry entry) {
//		return entryService.update(entry);
//	}
//	
//	@DeleteMapping("/{entryId}")
//	public Mono<Void> deleteEntry(BlueskyUser user, @PathVariable ObjectId entryId) {
//		return entryService.delete(user.getId(), entryId);
//	}
//}
