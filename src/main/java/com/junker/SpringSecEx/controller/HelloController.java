package com.junker.SpringSecEx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;


@RestController

public class HelloController {
	
	@GetMapping("/")
	public String greeting (HttpServletRequest request) {
		
		return "Welcome to here" + " " + request.getSession().getId();
	}
	
	

}
