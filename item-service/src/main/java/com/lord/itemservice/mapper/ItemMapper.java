package com.lord.itemservice.mapper;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.lord.itemservice.dto.ItemDto;
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
		itemDto.setProductName(item.getProductName());
		itemDto.setProductId(item.getProductId());
		itemDto.setDescription(item.getDescription());
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
		item.setProductName(itemDto.getProductName());
		item.setDescription(itemDto.getDescription());
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
	
	public List<ItemDto> itemStockDtoToItemDto(List<ItemDto> itemsDto,List<ItemStockDto> itemsStockDto){
		if(itemsStockDto==null) {
			return null;
		}
		List<ItemDto> listResult = new ArrayList<ItemDto>();
		ListIterator<ItemStockDto> stockIterator = itemsStockDto.listIterator();
		while(stockIterator.hasNext()){
			ItemDto itemResult = itemsDto.stream().filter(f -> f.getId()==stockIterator.next().getItemId()).findFirst().get();
			itemResult.setQuantity(stockIterator.next().getQuantity());
		listResult.add(itemResult);
		}
		return listResult;
		
		
	}
	
	public ItemDto testStockToDto(ItemStockDto itemStockDto) {
		if(itemStockDto==null) {
			return null;
		}
		ItemDto itemDto = new ItemDto();
		itemDto.setQuantity(itemStockDto.getQuantity());
		return itemDto;
	}
	
	public ItemDto itemStockDtoToItemDto(ItemDto itemDto,ItemStockDto itemStockDto) {
		if(itemStockDto==null) {
			return null;
		}
		itemDto.setQuantity(itemStockDto.getQuantity());
		return itemDto;
	}

}
