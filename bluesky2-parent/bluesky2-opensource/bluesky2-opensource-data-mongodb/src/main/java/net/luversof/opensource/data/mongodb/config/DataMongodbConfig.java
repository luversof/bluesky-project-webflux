package net.luversof.opensource.data.mongodb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@PropertySource("classpath:mongodb.properties")
@PropertySource("classpath:mongodb-${spring.profiles.active}.properties")
@EnableMongoAuditing
public class DataMongodbConfig {

}
