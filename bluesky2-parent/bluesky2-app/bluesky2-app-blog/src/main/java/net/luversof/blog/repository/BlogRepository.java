package net.luversof.blog.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import net.luversof.blog.domain.Blog;
import reactor.core.publisher.Mono;

@Repository
public interface BlogRepository extends ReactiveMongoRepository<Blog, UUID> {
	Mono<Blog> findByUserId(UUID userId);
}