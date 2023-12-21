package com.lord.productservice.utils;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RandomUUIDGenerator {
	
	@Bean
	UUID generateRandomUUID() {
		return UUID.randomUUID();
	}

}
