package net.luversof.web.blog.controller;

import java.time.Duration;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.luversof.blog.domain.Blog;
import net.luversof.blog.repository.BlogRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	private BlogRepository blogRepository;
	
	@GetMapping("/findAll")
	public Flux<Blog> getMapping() {
		return blogRepository.findAll();
	}
	
	@GetMapping(value = "/findAll2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Blog> getMapping2() {
		return blogRepository.findAll().log();
	}
	
	
	@GetMapping("/findByUserId")
	public Mono<Blog> findByUserId(@RequestParam UUID userId) {
		return blogRepository.findByUserId(userId);
	}
	
	
	@GetMapping(value = "/findByUserId2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Blog> findByUserId2(@RequestParam UUID userId) {
		return blogRepository.findByUserId(userId).flatMapMany(blog -> {
			Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
			Flux<Blog> blogFlux = Flux.fromStream(Stream.generate(() -> blog));
			return Flux.zip(interval, blogFlux).map(Tuple2::getT2);
		});
	}
	
	@PostMapping
	public void save(@RequestBody Mono<Blog> blogMono) {
		blogMono.subscribe(blogRepository::save);
	}
	
	@PostMapping("/saveAll")
	public void saveAll(@RequestBody Flux<Blog> blogFlux) {
		blogRepository.saveAll(blogFlux);
	}
	
	@PostMapping(value = "/saveAll2", consumes = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public void saveAll2(@RequestBody Flux<Blog> blogFlux) {
		blogRepository.saveAll(blogFlux);
	}
	
}
