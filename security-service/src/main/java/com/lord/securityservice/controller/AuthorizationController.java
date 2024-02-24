package com.lord.securityservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.lord.securityservice.dto.LoginDto;
import com.lord.securityservice.dto.LoginResponseDto;
import com.lord.securityservice.dto.RegistrationDto;
import com.lord.securityservice.mapper.DtoMapper;
import com.lord.securityservice.model.User;
import com.lord.securityservice.service.AuthenticationService;

@RestController
@RequestMapping("/api/authorization")
public class AuthorizationController {

	private static final Gson gson = new Gson();

	@Autowired
	private final DtoMapper dtoMapper;

	@Autowired
	private final AuthenticationService authenticationService;

	public AuthorizationController(DtoMapper dtoMapper, AuthenticationService authenticationService) {
		this.dtoMapper = dtoMapper;
		this.authenticationService = authenticationService;
	}

	@PostMapping("/register")
	ResponseEntity<String> registerUser(@RequestBody RegistrationDto registrationDto) {
		User user = dtoMapper.registrationDtoToUser(registrationDto);
		String register = authenticationService.registerUser(user);
		return new ResponseEntity<String>(gson.toJson(register), HttpStatus.CREATED);
	}

	@GetMapping("/login")
	ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto) {
		LoginResponseDto loginResponseDto = authenticationService.loginUser(loginDto);
		return new ResponseEntity<String>(gson.toJson(loginResponseDto.getJwtToken()), HttpStatus.OK);
	}

}
