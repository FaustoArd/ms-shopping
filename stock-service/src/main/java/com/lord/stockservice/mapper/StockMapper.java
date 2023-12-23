package com.lord.stockservice.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.lord.stockservice.dto.StockDto;
import com.lord.stockservice.model.Stock;

@Component
public class StockMapper {
	
	public Stock toStock(StockDto stockDto) {
		if(stockDto==null) {
			return null;
		}
		Stock stock = new Stock();
		stock.setQuantity(stockDto.getQuantity());
		stock.setProductIdCode(UUID.fromString(stockDto.getProductIdCode()));
		return stock;
	}
	
	public StockDto toStockDto(Stock stock){
		if(stock==null) {
			return null;
		}
		StockDto stockDto = new StockDto();
		stockDto.setId(stockDto.getId());
		stockDto.setQuantity(stock.getQuantity());
		stockDto.setProductIdCode(stock.getProductIdCode().toString());
		return stockDto;
	}
	
	
	
}
