package com.lord.securityservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping("/api/authorization")
public class AuthorizationController {
	
	private static final Gson gson = new Gson();
	
	@GetMapping
	String register() {
		return gson.toJson("Works!");
	}

}
