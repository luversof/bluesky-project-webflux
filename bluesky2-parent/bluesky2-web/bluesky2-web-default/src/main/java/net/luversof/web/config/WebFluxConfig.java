package net.luversof.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import net.luversof.blog.repository.BlogRepository;
import net.luversof.web.blog.handler.BlogHandler;

@Configuration
public class WebFluxConfig implements WebFluxConfigurer {

	@Bean
	public RouterFunction<ServerResponse> routes(BlogRepository blogRepository) {

		BlogHandler blogHandler = new BlogHandler(blogRepository);
		
		return RouterFunctions
				.route()
					.GET("/blog/search/findAll", blogHandler::findAll)
					.GET("/blog/search/findByUserId", blogHandler::getBlog)
				.build();
	}
}
