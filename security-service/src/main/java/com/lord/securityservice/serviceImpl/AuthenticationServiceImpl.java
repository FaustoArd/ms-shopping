package com.lord.securityservice.serviceImpl;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lord.securityservice.dto.LoginDto;
import com.lord.securityservice.dto.LoginResponseDto;
import com.lord.securityservice.mapper.DtoMapper;
import com.lord.securityservice.model.Role;
import com.lord.securityservice.model.User;
import com.lord.securityservice.repository.RoleRepository;
import com.lord.securityservice.service.AuthenticationService;
import com.lord.securityservice.service.JwtTokenService;
import com.lord.securityservice.service.UserService;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private final UserService userService;

	@Autowired
	private final RoleRepository roleRepository;

	@Autowired
	private final PasswordEncoder passwordEncoder;

	@Autowired
	private final AuthenticationManager authenticationManager;

	@Autowired
	private final JwtTokenService jwtTokenService;

	public AuthenticationServiceImpl(UserService userService, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
			JwtTokenService jwtTokenService) {
		this.roleRepository = roleRepository;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtTokenService = jwtTokenService;
		
	}

	@Override
	public String registerUser(User user){
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		Role userRole = roleRepository.findByAuthority("USER").orElseThrow(()-> new RuntimeException("Role no found"));
		Role savedUserRole = roleRepository.save(userRole);
		Set<Role> authorities = new HashSet<Role>();
		authorities.add(savedUserRole);
		User savedUser = new User();
		savedUser.setName(user.getName());
		savedUser.setLastname(user.getLastname());
		savedUser.setUsername(user.getUsername());
		savedUser.setEmail(user.getEmail());
		savedUser.setPassword(encodedPassword);
		savedUser.setAccountNonExpired(true);
		savedUser.setAccountNonLocked(true);
		savedUser.setCredentialsNonExpired(true);
		savedUser.setEnabled(true);
		savedUser.setAuthorities(authorities);
		userService.save(savedUser);
		return "User registered successfully!";

	}

	@Override
	public LoginResponseDto loginUser(LoginDto loginDto) {
		try {
			Authentication auth = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
			String jwtToken = jwtTokenService.generateJwt(auth);
			return new LoginResponseDto(jwtToken);
		}catch(AuthenticationException ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

}
