package com.lord.itemstockservice.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lord.itemstockservice.dto.ItemStockDto;
import com.lord.itemstockservice.model.ItemStock;

@Component
public class ItemStockMapper {

	
	public ItemStockDto toItemStockDto(ItemStock itemStock) {
		if(itemStock==null) {
			return  null;
		}
		ItemStockDto itemStockDto = new ItemStockDto();
		itemStockDto.setId(itemStock.getId());
		itemStockDto.setItemId(itemStock.getItemId());
		itemStockDto.setQuantity(itemStock.getQuantity());
	
		return itemStockDto;
	}
	
	public ItemStock toItemStock(ItemStockDto itemStockDto) {
		if(itemStockDto==null) {
			return null;
		}
		ItemStock itemStock = new ItemStock();
		itemStock.setId(itemStockDto.getId());
		itemStock.setQuantity(itemStockDto.getQuantity());
		itemStock.setItemId(itemStockDto.getItemId());
		
		return itemStock;
	}
	
	public List<ItemStockDto> toItemsStockDto(List<ItemStock> itemsStock){
		if( itemsStock == null) {
			return null;
		}
		List<ItemStockDto> itemsStockDto = new ArrayList<ItemStockDto>(itemsStock.size());
		for(ItemStock itemStock:itemsStock) {
			itemsStockDto.add(toItemStockDto(itemStock));
		}
		return itemsStockDto;
	}
	
	
	
}
