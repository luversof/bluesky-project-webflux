package net.luversof.web.blog.handler;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import net.luversof.blog.domain.Blog;
import net.luversof.blog.repository.BlogRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BlogHandler {

	private final BlogRepository blogRepository;
	
	public BlogHandler(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}
	
	public Mono<ServerResponse> findAll(ServerRequest request) {
		Flux<Blog> blogFlux = blogRepository.findAll();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(blogFlux, Blog.class);
	}
	
	public Mono<ServerResponse> findByUserId(ServerRequest request) {
		UUID userId = UUID.fromString(request.queryParam("userId").get());
		return blogRepository.findByUserId(userId)
				.flatMap(blog -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(blog)))
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
}
