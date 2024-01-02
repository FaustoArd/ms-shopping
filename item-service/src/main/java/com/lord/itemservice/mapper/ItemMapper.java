package com.lord.itemservice.mapper;


import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.lord.itemservice.dto.ItemDto;
import com.lord.itemservice.dto.ItemDtoOrderResponse;
import com.lord.itemservice.dto.ItemStockDto;
import com.lord.itemservice.model.Item;

@Component
public class ItemMapper {

	public ItemDto toItemDto(Item item) {
		if (item == null) {
			return null;
		}
		ItemDto itemDto = new ItemDto();
		itemDto.setId(item.getId().toString());
		itemDto.setItemName(item.getItemName());
		itemDto.setProductId(item.getProductId());
		itemDto.setDescription(item.getDescription());
		itemDto.setImageUrl(item.getImageUrl());
		itemDto.setItemSku(item.getItemSku());
		itemDto.setColor(item.getColor());
		itemDto.setPrice(item.getPrice());
		return itemDto;

	}

	public Item toItem(ItemDto itemDto) {
		if (itemDto == null) {
			return null;
		}
		Item item = new Item();
		if (itemDto.getId() != null) {
			item.setId(new ObjectId(itemDto.getId()));
		}
		item.setItemName(itemDto.getItemName());
		item.setDescription(itemDto.getDescription());
		item.setImageUrl(itemDto.getImageUrl());
		item.setProductId(itemDto.getProductId());
		item.setItemSku(itemDto.getItemSku());
		item.setColor(itemDto.getColor());
		item.setPrice(itemDto.getPrice());
		return item;
	}

	public List<ItemDto> toItemsDto(List<Item> items) {
		if (items == null) {
			return null;
		}
		List<ItemDto> itemsDto = new ArrayList<ItemDto>(items.size());
		for (Item item : items) {
			itemsDto.add(toItemDto(item));
		}
		return itemsDto;
	}
	
	public ItemStockDto toItemStockDto(String itemId,int quantity) {
		ItemStockDto itemStockDto = new ItemStockDto();
		itemStockDto.setItemId(itemId);
		itemStockDto.setQuantity(quantity);
		return itemStockDto;
	}
	
	public List<ItemDto> stocksToItemsDto(List<Item> items, List<ItemStockDto>  itemsStockDto){
		List<ItemDto> itemsDto = new ArrayList<ItemDto>(items.size()+1);
		itemsDto = items.stream().map(item -> {
			ItemDto itemDto = new ItemDto();
			itemDto.setId(item.getId().toString());
			itemDto.setItemName(item.getItemName());
			itemDto.setDescription(item.getDescription());
			itemDto.setImageUrl(item.getImageUrl());
			itemDto.setColor(item.getColor());
			itemDto.setProductId(item.getProductId());
			itemDto.setQuantity(itemsStockDto.stream().filter(stock -> stock.getItemId().equals(item.getId().toString()))
					.findFirst().get().getQuantity());
			itemDto.setItemSku(item.getItemSku());
			itemDto.setPrice(item.getPrice());
			return itemDto;
		}).toList();
		return itemsDto;
		
	}
	
	
		
		public ItemDto itemStockDtoToItemDto(ItemDto itemDto,ItemStockDto itemStockDto) {
		if(itemStockDto==null) {
			return null;
		}
		itemDto.setQuantity(itemStockDto.getQuantity());
		return itemDto;
	}
		
		public ItemDtoOrderResponse itemToItemDtoOrderResponse(Item item) {
			if(item==null) {
				return null;
			}
			ItemDtoOrderResponse itemDtoOrderResponse = new ItemDtoOrderResponse();
			itemDtoOrderResponse.setId(item.getId().toString());
			itemDtoOrderResponse.setPrice(item.getPrice());
			return itemDtoOrderResponse;
		}
		
		public Item searchToItem(String search) {
			if(search==null) {
				return null;
			}
			Item item = new Item();
			item.setDescription(search);
			
			return item;
		}

}
