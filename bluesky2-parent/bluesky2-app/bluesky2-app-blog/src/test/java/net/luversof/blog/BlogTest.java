package net.luversof.blog;

import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import net.luversof.blog.domain.Blog;
import net.luversof.blog.repository.BlogRepository;
import net.luversof.test.GeneralTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
public class BlogTest extends GeneralTest {
	
	@Autowired
	private BlogRepository blogRepository;

	@Test
	public void test() {
		log.debug("Test : {}", "sfsd");
		
		Blog blog = new Blog();
		blog.setUserId(UUID.randomUUID());
		
		log.debug("test22 : {}", blogRepository.save(blog).block());
//		blogRepository.findAll().log();
		
	}
	
	@Test
	public void test2() {
		Mono<Blog> findByUserId = blogRepository.findByUserId(UUID.fromString("39bada3b-9047-4c81-8386-d58854e802fa"));
		log.debug("test : {}", findByUserId.block());
	}
	
	@Test
	public void test3() {
		log.debug("start");
		Flux<Blog> blogFlux = blogRepository.findAll().log();
		
		StepVerifier.create(blogFlux)
		.assertNext(blog -> {
			log.debug("assert Test : {}", blog);
			assertNotNull(blog.getId());
		})
		.expectNextCount(1)
		.expectComplete()
		.verify();
	}
}
