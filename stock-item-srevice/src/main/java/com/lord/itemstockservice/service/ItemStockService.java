package com.lord.itemstockservice.service;

import java.util.List;

import com.lord.itemstockservice.model.ItemStock;

public interface ItemStockService {

	
	public ItemStock findById(Long id);
	
	public ItemStock findByItemId(String itemId);
	
	public ItemStock save(String productId,int quantity);
	
	public List<ItemStock> findAll();
	
	
}
