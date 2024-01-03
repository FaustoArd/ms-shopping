package com.lord.itemmanagementservice.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import com.lord.itemmanagementservice.dto.ItemManagementDto;
import com.lord.itemmanagementservice.model.ItemManagement;

@Component
public class ItemManagementMapper {
	
	public ItemManagementDto toItemManagementDto(ItemManagement itemManagement) {
		if(itemManagement==null) {
			return null;
		}
		ItemManagementDto itemManagementDto = new ItemManagementDto();
		itemManagementDto.setId(itemManagement.getId().toString());
		itemManagementDto.setProductId(itemManagement.getProductId());
		itemManagementDto.setItemId(itemManagement.getItemId().toString());
		itemManagementDto.setMaxShare(itemManagement.getMaxShare());
		itemManagementDto.setPricesByShare(itemManagement.getPricesByShare());
		itemManagementDto.setPriceByMaxShare(itemManagement.getPriceByMaxShare());
		itemManagementDto.setInterest(itemManagement.getInterest());
		return itemManagementDto;
		
	}
	
	public ItemManagement toItemManagement(ItemManagementDto itemManagementDto) {
		if(itemManagementDto==null) {
			return null;
		}
		ItemManagement itemManagement = new ItemManagement();
		itemManagement.setId(new ObjectId(itemManagementDto.getId()));
		itemManagement.setProductId(itemManagementDto.getProductId());
		itemManagement.setItemId(new ObjectId(itemManagementDto.getItemId()));
		itemManagement.setMaxShare(itemManagementDto.getMaxShare());
		itemManagement.setPricesByShare(itemManagement.getPricesByShare());
		itemManagement.setPriceByMaxShare(itemManagement.getPriceByMaxShare());
		itemManagement.setInterest(itemManagement.getInterest());
		return itemManagement;
	}
	
	public List<ItemManagementDto> toItemsManagementDto(List<ItemManagement> itemsManagement){
		if(itemsManagement==null) {
			return null;
		}
		List<ItemManagementDto> itemsManagementDto = itemsManagement.stream().map(this::toItemManagementDto).collect(Collectors.toList());
		return itemsManagementDto;
	}
	

}
