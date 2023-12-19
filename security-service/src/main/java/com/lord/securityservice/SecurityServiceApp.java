package com.lord.securityservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lord.securityservice.model.Role;
import com.lord.securityservice.repository.RoleRepository;

@SpringBootApplication
public class SecurityServiceApp {
	
	public static void main(String[] args) {
		SpringApplication.run(SecurityServiceApp.class, args);
	}
	
	@Bean
	CommandLineRunner run(RoleRepository roleRepository) {
		return args ->{
			roleRepository.save(new Role("ADMIN")) ;
			roleRepository.save(new Role("USER"));
		};
	}

}
