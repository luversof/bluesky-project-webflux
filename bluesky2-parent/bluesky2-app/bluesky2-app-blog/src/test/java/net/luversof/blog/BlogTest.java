package net.luversof.blog;

import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import net.luversof.blog.domain.Blog;
import net.luversof.blog.repository.BlogRepository;
import net.luversof.test.GeneralTest;
import reactor.core.publisher.Mono;

@Slf4j
public class BlogTest extends GeneralTest {
	
	@Autowired
	private BlogRepository blogRepository;

	@Test
	public void test() {
		log.debug("Test : {}", "sfsd");
		
		Blog blog = new Blog();
		blog.setId(UUID.randomUUID());
		blog.setUserId(UUID.randomUUID());
		log.debug("test22 : {}", blogRepository.save(blog).block());
//		blogRepository.findAll().log();
		log.debug("test2 : {}", blogRepository.findAll().blockFirst());
	}
	
	@Test
	public void test2() {
		Mono<Blog> findByUserId = blogRepository.findByUserId(UUID.fromString("ad8b89eb-4df3-454d-a640-537fa0104aef"));
		log.debug("test : {}", findByUserId.block());
	}
}
