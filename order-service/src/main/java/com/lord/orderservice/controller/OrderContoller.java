package com.lord.orderservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.lord.orderservice.dto.OrderDto;
import com.lord.orderservice.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderContoller {
	
	@Autowired
	private final OrderService orderService;
	
	private static final Gson gson = new Gson();

	public OrderContoller(OrderService orderService) {
		super();
		this.orderService = orderService;
	}
	
	@PostMapping("/")
	ResponseEntity<String> placeOrder(@RequestBody OrderDto orderDto){
		BigDecimal result = orderService.placeOrder(orderDto);
		return new ResponseEntity<String>(gson.toJson("Total order price: $" + result),HttpStatus.CREATED);
	}
}
