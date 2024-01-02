package com.lord.itemmanagementservice.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.lord.itemmanagementservice.model.ItemManagement;

public interface ItemManagementRepository extends MongoRepository<ItemManagement, ObjectId>{

}
