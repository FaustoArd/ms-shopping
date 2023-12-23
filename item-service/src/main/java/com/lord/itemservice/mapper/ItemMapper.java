package com.lord.itemservice.mapper;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import com.lord.itemservice.dto.ItemDto;
import com.lord.itemservice.model.Item;


public class ItemMapper {
	
	public ItemDto toItemDto(Item item) {
		if(item==null) {
			return null;
		}
		ItemDto itemDto = new ItemDto();
		itemDto.setId(item.getId().toString());
		itemDto.setProductName(item.getProductName());
		itemDto.setDescription(item.getDescription());
		itemDto.setColor(item.getColor());
		return itemDto;
		
	}
	
	public Item toItem(ItemDto itemDto) {
		if(itemDto==null) {
			return null;
		}
		Item item = new Item();
		item.setId(new ObjectId(itemDto.getId()));
		item.setProductName(itemDto.getProductName());
		item.setDescription(itemDto.getDescription());
		item.setColor(itemDto.getColor());
		return item;
	}

}
