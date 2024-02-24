package com.lord.securityservice.mapper;

import org.springframework.stereotype.Component;

import com.lord.securityservice.dto.LoginDto;
import com.lord.securityservice.dto.RegistrationDto;
import com.lord.securityservice.model.User;

@Component
public class DtoMapper {
	
	
	
	public User registrationDtoToUser(RegistrationDto registrationDto) {
	if(registrationDto==null) {
		return null;
	}
	 User user = new User(
			registrationDto.getName()
			,registrationDto.getLastname()
			,registrationDto.getUsername()
			,registrationDto.getEmail()
			,registrationDto.getPassword());
	return user;
	}
	
	public User loginDtoToUser(LoginDto loginDto) {
		if(loginDto==null) {
			return null;
		}
		User user = new User(loginDto.getUsername(),loginDto.getPassword());
		return user;
	}
	
	

}
