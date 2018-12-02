package net.luversof.blog;

import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import net.luversof.blog.domain.Blog;
import net.luversof.blog.repository.BlogRepository;
import net.luversof.test.GeneralTest;

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
		log.debug("test2 : {}", blogRepository.findAll().blockFirst());
	}
}
