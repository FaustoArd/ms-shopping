package com.lord.purchasecartservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PurchaseCartServiceApp {

	public static void main(String[] args) {
		SpringApplication.run(PurchaseCartServiceApp.class, args);
	}
}
