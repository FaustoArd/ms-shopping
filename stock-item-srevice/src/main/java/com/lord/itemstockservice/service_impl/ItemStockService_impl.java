package com.lord.itemstockservice.service_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lord.itemstockservice.model.ItemStock;
import com.lord.itemstockservice.repository.ItemStockRepository;
import com.lord.itemstockservice.service.ItemStockService;

@Service
public class ItemStockService_impl implements ItemStockService {

	@Autowired
	private final ItemStockRepository itemStockRepository;
	
	public ItemStockService_impl(ItemStockRepository itemStockRepository) {
		this.itemStockRepository = itemStockRepository;
	}

	@Override
	public ItemStock findById(Long id) {
		return itemStockRepository.findById(id).orElseThrow(() ->  new RuntimeException("Stock not found"));
	}
	
	@Override
	public ItemStock findByItemId(String itemId) {
	return itemStockRepository.findByItemId(itemId).orElseThrow(() ->  new RuntimeException("Stock not found"));
	}
	
	@Override
	public ItemStock save(String productId, int quantity) {
	ItemStock itemStock = new ItemStock();
	itemStock.setItemId(productId);
	itemStock.setQuantity(quantity);
		return itemStockRepository.save(itemStock);
	}

	@Override
	public List<ItemStock> findAll() {
		return (List<ItemStock>)itemStockRepository.findAll();
	}


	
	
}

