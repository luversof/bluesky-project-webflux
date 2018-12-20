package net.luversof.web.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.SneakyThrows;

@Controller
public class IndexController {

	@GetMapping({ "/", "/index" })
	@SneakyThrows
	public @ResponseBody String index() {
		return "test";
	}
}
