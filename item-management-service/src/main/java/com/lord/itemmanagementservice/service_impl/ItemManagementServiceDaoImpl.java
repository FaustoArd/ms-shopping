package com.lord.itemmanagementservice.service_impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import com.lord.itemmanagementservice.dto.ItemManagementDto;
import com.lord.itemmanagementservice.mapper.ItemManagementMapper;
import com.lord.itemmanagementservice.model.ItemManagement;
import com.lord.itemmanagementservice.repository.ItemManagementRepository;
import com.lord.itemmanagementservice.service.ItemManagementServiceDao;

public class ItemManagementServiceDaoImpl implements ItemManagementServiceDao {
	
	
	@Autowired
	private final ItemManagementRepository itemManagementRepository;
	
	@Autowired
	private final ItemManagementMapper itemManagementMapper;
	
	public ItemManagementServiceDaoImpl(ItemManagementRepository itemManagementRepository,ItemManagementMapper itemManagementMapper) {
		this.itemManagementRepository = itemManagementRepository;
		this.itemManagementMapper = itemManagementMapper;
	}
	
	@Override
	public List<ItemManagement> findByProductId(Long productId) {
		return itemManagementRepository.findByProductId(productId);
	}

	@Override
	public List<ItemManagement> findAllByItemId(List<ObjectId> itemId) {
	return itemManagementRepository.findAllByItemId(itemId);
	}

	@Override
	public ItemManagement save(ItemManagementDto itemManagementDto) {
		ItemManagement itemManagement = itemManagementMapper.toItemManagement(itemManagementDto);
		return itemManagement;
	}

	@Override
	public ItemManagement findByItemId(ObjectId itemId) {
	 return itemManagementRepository.findByItemId(itemId).orElseThrow(()-> new RuntimeException("Item Management not found"));
	}
}
