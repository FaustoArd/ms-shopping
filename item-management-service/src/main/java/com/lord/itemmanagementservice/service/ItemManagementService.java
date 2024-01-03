package com.lord.itemmanagementservice.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.lord.itemmanagementservice.dto.ItemManagementDto;
import com.lord.itemmanagementservice.model.ItemManagement;

public interface ItemManagementService {
	
	public BigDecimal calculatePriceByMaxShare(BigDecimal price,int maxShare, BigDecimal interest);
	
	public Map<Integer,BigDecimal> calculatePricesByShare(BigDecimal price,int maxShare, BigDecimal interest);
	
	public List<ItemManagement> findByProductId(Long productId);
	
	public List<ItemManagement> findAllByItemId(List<ObjectId> itemId);
	
	public ItemManagement save(ItemManagementDto itemManagementDto);
	
	public ItemManagement findByItemId(ObjectId itemId);

}
