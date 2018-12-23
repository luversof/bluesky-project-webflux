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
@EnableReactiveMongoRepositories(basePackages = "net.luversof.notification.repository", reactiveMongoTemplateRef = "notificationReactiveMongoTemplate")
public class NotificationReactiveMongoConfiguration {
	
	@Bean
	public SimpleReactiveMongoDatabaseFactory notificationReactiveMongoDatabaseFactory(MongoClient mongoClient) {
		return new SimpleReactiveMongoDatabaseFactory(mongoClient, "notification");
	}
	
	@Bean
	public ReactiveMongoTemplate notificationReactiveMongoTemplate(@Qualifier("notificationReactiveMongoDatabaseFactory") ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory, MongoConverter converter) {
		return new ReactiveMongoTemplate(reactiveMongoDatabaseFactory, converter);
	}
}
