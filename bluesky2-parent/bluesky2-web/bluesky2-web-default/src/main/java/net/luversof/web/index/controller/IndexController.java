package net.luversof.web.index.controller;

import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

//	@GetMapping({ "/", "/index" })
//	public String index() {
//		return "index";
//	}

//	@RequestMapping(value = "/bookkeeping/**", method = RequestMethod.GET)
//	public String html5Forwarding() {
//		return "forward:/index.html";
//	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/testSecurity")
	public @ResponseBody Authentication testSecurity(@Nullable Authentication authentication) {
		return authentication;
	}
}
