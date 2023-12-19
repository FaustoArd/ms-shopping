package com.lord.securityservice.service;

import org.springframework.security.core.Authentication;

public interface JwtTokenService {

	public String generateJwt(Authentication auth);
}
