package com.lord.itemservice.service;

import java.math.BigDecimal;
import java.util.List;

import org.bson.types.ObjectId;

import com.lord.itemservice.dto.ItemDto;
import com.lord.itemservice.dto.ItemDtoOrderResponse;
import com.lord.itemservice.dto.ItemManagementDto;
import com.lord.itemservice.dto.ItemStockDto;
import com.lord.itemservice.model.Item;

public interface ItemService {
	
	public List<ItemDto> findAllByProductId(Long productId);
	
	public ItemDto findById(String id);
	
	public ItemDto findByItemSku(String itemSku);
	
	public ItemDto save(ItemDto itemDto);
	
	public int saveStock(String itemId,int quantity);
	
	public int findTotalProductQuantity(List<String> itemsId);
	
	public BigDecimal getItemPrice(String itemId);
	
	public ItemDtoOrderResponse findByIdToPlaceOrder(String itemId);
	
	public List<ItemDto> findBySearch(String search);
	
	public ItemManagementDto findItemsSharePrice();
	

	
	
	
	
}
