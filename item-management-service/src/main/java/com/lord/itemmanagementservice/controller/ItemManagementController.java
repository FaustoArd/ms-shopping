package com.lord.itemmanagementservice.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lord.itemmanagementservice.service.ItemManagementService;

@RestController
@RequestMapping("/api/item-management")
public class ItemManagementController {
	
	@Autowired
	private final ItemManagementService itemManagementService;
	
	public ItemManagementController(ItemManagementService itemManagementService) {
		this.itemManagementService = itemManagementService;
	}
	
	@GetMapping("/calculate_prices_by_share")
	ResponseEntity<Map<Integer, BigDecimal>> calculatePricesbyShare(@RequestParam("price")BigDecimal price, @RequestParam("maxShare")int maxShare,
			@RequestParam("interest")BigDecimal interest){
		Map<Integer, BigDecimal> prices = itemManagementService.calculatePricesByShare(price, maxShare, interest);
		return ResponseEntity.ok(prices);
	}
	
	@GetMapping("/calculate_max_share")
	ResponseEntity<BigDecimal> calculateMaxShare(@RequestParam("price")BigDecimal price,@RequestParam("maxShare")int maxShare,
			@RequestParam("interest")BigDecimal interest){
		BigDecimal priceByMaxShare = itemManagementService.calculatePriceByMaxShare(price, maxShare, interest);
		return ResponseEntity.ok(priceByMaxShare);
	}

}
