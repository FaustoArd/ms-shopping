package com.lord.itemstockservice.service;

import java.util.List;

import com.lord.itemstockservice.dto.ItemStockDto;
import com.lord.itemstockservice.model.ItemStock;

public interface ItemStockService {

	
	public ItemStock findById(Long id);
	
	public ItemStockDto findByItemId(String itemId);
	
	public ItemStockDto save(ItemStockDto itemStockDto);
	
	public int updateStock(String itemId,int quantity);
	
	public List<ItemStockDto> findAll();
	
	public List<ItemStockDto> findAllbyItemId(List<String>itemsId);
	
	public boolean isInStock(String itemId,int quantity);
	
	
}
