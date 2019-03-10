package net.luversof.opensource.data.mongodb.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "net.luversof.bookkeeping.repository", reactiveMongoTemplateRef = "bookkeepingReactiveMongoTemplate")
public class BookkeepingReactiveMongoConfiguration {
	
	@Bean
	public SimpleReactiveMongoDatabaseFactory bookkeepingReactiveMongoDatabaseFactory(MongoClient mongoClient) {
		return new SimpleReactiveMongoDatabaseFactory(mongoClient, "bookkeeping");
	}
	
	@Bean
	public ReactiveMongoTemplate bookkeepingReactiveMongoTemplate(@Qualifier("bookkeepingReactiveMongoDatabaseFactory") ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory, MongoConverter converter) {
		return new ReactiveMongoTemplate(reactiveMongoDatabaseFactory, converter);
	}
}
