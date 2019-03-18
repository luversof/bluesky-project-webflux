package net.luversof.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.spring5.view.reactive.ThymeleafReactiveViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
//@EnableWebFlux
public class WebFluxConfig implements WebFluxConfigurer {
	
//	@Autowired
//	private ThymeleafReactiveViewResolver thymeleafReactiveViewResolver;
//	
//	@Autowired
//	private ObjectMapper objectMapper;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET", "POST").allowCredentials(false);
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
