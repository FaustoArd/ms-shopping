package com.lord.oauth2authorizationserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
	
	@GetMapping
	ResponseEntity<String> hello(){
		return new ResponseEntity<String>("Auth!",HttpStatus.OK);
	}

}
