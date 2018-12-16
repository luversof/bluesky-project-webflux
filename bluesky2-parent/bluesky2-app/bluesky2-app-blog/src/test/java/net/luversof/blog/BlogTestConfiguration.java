package net.luversof.blog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Flux;

@Configuration
public class BlogTestConfiguration {

	@Bean
	CommandLineRunner demo() {
		return args -> {
		Flux<String> x = Flux.fromArray("1,2,3,4".split(","));
		x.map(Integer::parseInt)
			.filter(i -> i % 2 == 0)
			.subscribe(System.out::println, null, null);
		};
	}
}
