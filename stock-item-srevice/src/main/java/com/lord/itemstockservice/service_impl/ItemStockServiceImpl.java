package com.lord.itemstockservice.service_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lord.itemstockservice.dto.ItemStockDto;
import com.lord.itemstockservice.mapper.ItemStockMapper;
import com.lord.itemstockservice.model.ItemStock;
import com.lord.itemstockservice.repository.ItemStockRepository;
import com.lord.itemstockservice.service.ItemStockService;

@Service
public class ItemStockServiceImpl implements ItemStockService {

	@Autowired
	private final ItemStockRepository itemStockRepository;
	
	private final ItemStockMapper itemStockMapper;
	
	public ItemStockServiceImpl(ItemStockRepository itemStockRepository,ItemStockMapper itemStockMapper) {
		this.itemStockRepository = itemStockRepository;
		this.itemStockMapper = itemStockMapper;
	}

	@Override
	public ItemStock findById(Long id) {
		return itemStockRepository.findById(id).orElseThrow(() ->  new RuntimeException("Stock not found"));
	}
	
	@Override
	public ItemStockDto findByItemId(String itemId) {
	ItemStock itemStock =  itemStockRepository.findByItemId(itemId).orElseThrow(() ->  new RuntimeException("Stock not found"));
	return itemStockMapper.toItemStockDto(itemStock);
	}
	
	@Override
	public ItemStockDto save(ItemStockDto itemStockDto) {
	ItemStock itemStock = itemStockMapper.toItemStock(itemStockDto);
		return itemStockMapper.toItemStockDto(itemStockRepository.save(itemStock));
	}

	@Override
	public List<ItemStock> findAll() {
		return (List<ItemStock>)itemStockRepository.findAll();
	}


	
	
}

