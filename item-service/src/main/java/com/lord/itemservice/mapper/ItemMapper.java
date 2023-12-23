package com.lord.itemservice.mapper;

import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.lord.itemservice.dto.ItemDto;
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

}
