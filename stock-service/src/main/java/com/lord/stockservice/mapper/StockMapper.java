package com.lord.stockservice.mapper;



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
		stock.setProductId(stockDto.getProductId());
		return stock;
	}
	
	public StockDto toStockDto(Stock stock){
		if(stock==null) {
			return null;
		}
		StockDto stockDto = new StockDto();
		stockDto.setId(stockDto.getId());
		stockDto.setQuantity(stock.getQuantity());
		stockDto.setProductId(stock.getProductId());
		return stockDto;
	}
	
	
	
}
