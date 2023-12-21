package com.lord.stockservice.service_impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lord.stockservice.model.Stock;
import com.lord.stockservice.repository.StockRepository;
import com.lord.stockservice.service.StockService;

@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	private final StockRepository stockRepository;

	public StockServiceImpl(StockRepository stockRepository) {
		super();
		this.stockRepository = stockRepository;
	}

	@Override
	public void save(Stock stock) {
		
	stockRepository.save(stock);
		
	}

	@Override
	public Stock findById(Long id) {
		return stockRepository.findById(id).orElseThrow(()-> new RuntimeException("Stock not found"));
	}

	@Override
	public boolean isAvailable(String productIdCode) {
		Stock stock =  stockRepository.findByProductIdCode(UUID.fromString(productIdCode)).orElseThrow(()-> new RuntimeException("Stock not found"));
		return stock.isAvailable();

	}

	
	
	

}
