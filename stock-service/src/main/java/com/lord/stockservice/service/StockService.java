package com.lord.stockservice.service;

import java.util.UUID;

import com.lord.stockservice.model.Stock;

public interface StockService {

	public void save(Stock stock);
	
	public Stock findById(Long id);
	
	public boolean isAvailable(String productIdCode);
	
}
