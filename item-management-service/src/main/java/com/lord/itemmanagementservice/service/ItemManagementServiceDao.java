package com.lord.itemmanagementservice.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.lord.itemmanagementservice.dto.ItemManagementDto;
import com.lord.itemmanagementservice.model.ItemManagement;

public interface ItemManagementServiceDao {
	public List<ItemManagement> findByProductId(Long productId);
	
	public List<ItemManagement> findAllByItemId(List<ObjectId> itemId);
	
	public ItemManagement save(ItemManagementDto itemManagementDto);
	
	public ItemManagement findByItemId(ObjectId itemId);
}
