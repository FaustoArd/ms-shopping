package com.lord.securityservice.serviceImpl;

import java.time.Instant;
import java.util.Calendar;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.lord.securityservice.service.JwtTokenService;


@Service
public class JwtTokenServiceImpl implements JwtTokenService {
	
	@Autowired
	private JwtEncoder jwtEncoder;
	
	@Override
	public String generateJwt(Authentication auth) {
	Instant now = Instant.now();
	String scope = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
	
	JwtClaimsSet claims = JwtClaimsSet.builder()
			.issuer("self")
			.issuedAt(now)
			.subject(auth.getName())
			.claim("roles", scope)
			.build();
	
	return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}

}
