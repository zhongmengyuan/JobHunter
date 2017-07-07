package com.yuan.jobhunter.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestHelloWorld {

	@RequestMapping(value = "/hello")
	public String hello() {
		return "hello!";
	}

}
