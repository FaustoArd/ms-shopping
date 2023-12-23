package com.lord.itemservice.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.lord.itemservice.model.Item;

public interface ItemRepository extends MongoRepository<Item, ObjectId>{

}
