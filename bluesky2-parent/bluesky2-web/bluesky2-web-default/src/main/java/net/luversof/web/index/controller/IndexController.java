package net.luversof.web.index.controller;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
	
	@Autowired
	private AnnotationConfigReactiveWebServerApplicationContext ctx; 

	@GetMapping({ "/", "/index" })
	public @ResponseBody Environment index() {
		return ctx.getEnvironment();
	}
}
