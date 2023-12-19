package com.lord.productservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	private static final Gson gson = new Gson();
	
	@GetMapping("/")
	ResponseEntity<String> hello(){
		return new ResponseEntity<String>(gson.toJson("Hola!"),HttpStatus.OK);
	}

}
