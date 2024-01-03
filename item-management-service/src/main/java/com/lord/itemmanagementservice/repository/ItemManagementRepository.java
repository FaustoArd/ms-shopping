package com.lord.itemmanagementservice.repository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


import com.lord.itemmanagementservice.model.ItemManagement;

public interface ItemManagementRepository extends MongoRepository<ItemManagement, ObjectId>{
	
	public List<ItemManagement> findByProductId(Long id);
	
	public Optional<ItemManagement> findByItemId(ObjectId itemId);
	
	public List<ItemManagement> findAllByItemId(List<ObjectId> itemId);

}
