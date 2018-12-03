package net.luversof.opensource.data.monbodb.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "net.luversof.blog.repository", reactiveMongoTemplateRef = "blogReactiveMongoTemplate")
@AutoConfigureAfter
public class BlogReactiveMongoConfiguration {
	
	@Bean
	public SimpleReactiveMongoDatabaseFactory blogReactiveMongoDatabaseFactory(MongoClient mongoClient) {
		return new SimpleReactiveMongoDatabaseFactory(mongoClient, "blog");
	}
	
	@Bean
	public ReactiveMongoTemplate blogReactiveMongoTemplate(@Qualifier("blogReactiveMongoDatabaseFactory") ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory, MongoConverter converter) {
		return new ReactiveMongoTemplate(reactiveMongoDatabaseFactory, converter);
	}
}
