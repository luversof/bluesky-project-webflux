package net.luversof.test;


import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.FilterType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:test.properties")
@ComponentScan(basePackages = "net.luversof", excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "net\\.luversof\\.boot\\..*"))
public class TestApplication {
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
}
