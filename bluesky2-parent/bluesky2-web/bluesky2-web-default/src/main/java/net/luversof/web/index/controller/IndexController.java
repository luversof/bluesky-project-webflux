package net.luversof.web.index.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	@GetMapping({ "/", "/index" })
	public String index() {
		return "index";
	}
	
	@GetMapping("/testSecurity")
	public @ResponseBody Authentication testSecurity(Authentication authentication) {
		return authentication;
	}
}
