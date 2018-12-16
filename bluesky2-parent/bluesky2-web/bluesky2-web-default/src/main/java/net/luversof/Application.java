package net.luversof;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class Application {

	public static void main(String[] args) throws Throwable {
		new SpringApplicationBuilder()
			.sources(Application.class)
			.properties("classpath:a.properties", "classpath:b.properties", "classpath:c.properties")
			.bannerMode(Banner.Mode.OFF)
			.build()
			.run(args);
//    	SpringApplication springApplication = new SpringApplication(Application.class);
//    	springApplication.run(args);
	}

}
