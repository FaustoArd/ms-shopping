package com.lord.securityservice.service;

import com.lord.securityservice.dto.LoginDto;
import com.lord.securityservice.dto.LoginResponseDto;
import com.lord.securityservice.dto.RegistrationDto;
import com.lord.securityservice.model.User;

public interface AuthenticationService {

	
	public String registerUser(User user);
	
	public LoginResponseDto loginUser(LoginDto loginDto);
}
