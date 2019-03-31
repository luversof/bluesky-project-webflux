package net.luversof.web.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.luversof.boot.autoconfigure.data.mongo.databind.module.ObjectIdSerializerModule;
import net.luversof.web.reactive.result.method.UserDetailsHandlerMethodArgumentResolver;

@Configuration
//@EnableWebFlux
public class WebFluxConfig implements WebFluxConfigurer {
	
//	@Autowired
//	private ThymeleafReactiveViewResolver thymeleafReactiveViewResolver;
//	
	@Autowired
	private ObjectMapper objectMapper;
	
	@PostConstruct
	public void postConstruct() {
		objectMapper.registerModule(new ObjectIdSerializerModule());
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowCredentials(false);
	}
	
	@Bean
	public UserDetailsHandlerMethodArgumentResolver userDetailsHandlerMethodArgumentResolver() {
		return new UserDetailsHandlerMethodArgumentResolver(ReactiveAdapterRegistry.getSharedInstance());
	}
	
//	@Bean
//	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
//		return new HiddenHttpMethodFilter();
//	}
//
//	@Override
//	public void configureViewResolvers(ViewResolverRegistry registry) {
//		registry.viewResolver(thymeleafReactiveViewResolver);
//		WebFluxConfigurer.super.configureViewResolvers(registry);
//	}
	
	

//	@Bean
//	public RouterFunction<ServerResponse> routes(BlogRepository blogRepository) {
//
//		BlogHandler blogHandler = new BlogHandler(blogRepository);
//		
//		return RouterFunctions
//				.route()
//					.GET("/blog/search/findAll", blogHandler::findAll)
//					.GET("/blog/search/findByUserId", blogHandler::findByUserId)
//				.build();
//	}
}
