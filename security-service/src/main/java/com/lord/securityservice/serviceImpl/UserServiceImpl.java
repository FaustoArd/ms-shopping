package com.lord.securityservice.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.lord.securityservice.model.User;
import com.lord.securityservice.repository.UserRepository;

import com.lord.securityservice.service.UserService;

@Service
public class UserServiceImpl implements UserService,UserDetailsService{

	@Autowired
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User findById(Long id) {
	return userRepository.findById(id).orElseThrow(()-> new RuntimeException());
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException("User name not found"));
	}

	

	
	




	

	

}
