package com.lord.itemservice.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.lord.itemservice.dto.ItemDto;
import com.lord.itemservice.model.Item;

public interface ItemService {

	public List<Item> findAllByProductId(Long productId);
	
	public Item findById(ObjectId id);
	
	public String save(ItemDto itemDto);
	
	
}
