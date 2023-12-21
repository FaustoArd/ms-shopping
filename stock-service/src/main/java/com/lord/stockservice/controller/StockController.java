package com.lord.stockservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.lord.stockservice.dto.StockDto;
import com.lord.stockservice.mapper.StockMapper;
import com.lord.stockservice.model.Stock;
import com.lord.stockservice.service.StockService;

@RestController
@RequestMapping("/api/stock")
public class StockController {
	
	@Autowired
	private final StockService stockService;
	private final StockMapper stockMapper;

	public StockController(StockService stockService,StockMapper stockMapper) {
		this.stockService = stockService;
		this.stockMapper = stockMapper;
	}
	
	 
	
	@GetMapping("/is_available")
	ResponseEntity<Boolean> isAvailable(@RequestParam("productIdCode") String productIdCode){
		return new ResponseEntity<Boolean>(stockService.isAvailable(productIdCode),HttpStatus.OK);
	}
	
	@PostMapping("/")
	ResponseEntity<String> save(@RequestBody StockDto stockDto){
	Stock stock = stockMapper.toStock(stockDto);
	stockService.save(stock);
	return new ResponseEntity<String>(new Gson().toJson("Stock saved"),HttpStatus.CREATED);
	}
}
