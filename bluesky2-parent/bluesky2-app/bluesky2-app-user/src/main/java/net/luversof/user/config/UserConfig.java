package net.luversof.user.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import net.luversof.user.service.EmptyLoginUserService;
import net.luversof.user.service.LoginUserService;

public class UserConfig {
	@Bean
	@ConditionalOnMissingBean
	public LoginUserService loginUserService() {
		return new EmptyLoginUserService();
	}
}
