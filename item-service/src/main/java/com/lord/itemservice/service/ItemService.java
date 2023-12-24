package com.lord.itemservice.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.lord.itemservice.dto.ItemDto;
import com.lord.itemservice.model.Item;

public interface ItemService {

	public List<ItemDto> findAllByProductId(Long productId);
	
	public ItemDto findById(String id);
	
	public ItemDto findByItemSku(String itemSku);
	
	public String save(ItemDto itemDto);
	
	public int saveStock(String itemId,int quantity);
	
	
}
