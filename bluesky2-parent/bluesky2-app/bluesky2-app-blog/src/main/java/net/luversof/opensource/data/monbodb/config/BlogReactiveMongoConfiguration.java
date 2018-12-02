package net.luversof.opensource.data.monbodb.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "net.luversof.blog.repository")
@AutoConfigureAfter
public class BlogReactiveMongoConfiguration extends AbstractReactiveMongoConfiguration {

	@Override
	public MongoClient reactiveMongoClient() {
		return MongoClients.create("mongodb://192.168.0.11:27017/blog");
	}

	@Override
	protected String getDatabaseName() {
		return "blog";
	}

}
