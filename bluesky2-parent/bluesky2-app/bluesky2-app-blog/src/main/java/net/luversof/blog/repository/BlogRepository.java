package net.luversof.blog.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import net.luversof.blog.domain.Blog;

@Repository
public interface BlogRepository extends ReactiveMongoRepository<Blog, Long> {

}
