package com.lord.itemstockservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ItemStockServiceApp {

	public static void main(String[] args) {
		SpringApplication.run(ItemStockServiceApp.class, args);
	}
}
