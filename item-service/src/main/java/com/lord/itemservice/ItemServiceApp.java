package com.lord.itemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ItemServiceApp {
	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApp.class, args);
	}

}