package com.lord.itemservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class ItemServiceApp {
	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApp.class, args);
	}
	
	/*@Bean
	CommandLineRunner run(MongoTemplate mongoTemplate) {
		return args ->{
			mongoTemplate.getDb().drop();
			
		};
	}*/

}
