package com.lord.categoryservice.utils;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class RandomUUIDGenerator {
	
	@Bean
	UUID generateRandomUUID() {
		return UUID.randomUUID();
	}

}
