package net.luversof.web.user.controller;

import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
@RequestMapping(value= "/api/user/loginInfo")
public class LoginInfoController {

	@GetMapping
	public LoginInfo loginInfo(Authentication authentication) {
		LoginInfo loginInfo = new LoginInfo();
		
		if (authentication == null) {
			return loginInfo;
		}
		
		loginInfo.setLogin(true);
		if (authentication.getPrincipal() instanceof AuthenticatedPrincipal) {
			loginInfo.setName(((AuthenticatedPrincipal) authentication.getPrincipal()).getName());
		}
		return loginInfo;
	}
	
	@Data
	public class LoginInfo {
		private boolean isLogin;
		private String name;
	}
}
