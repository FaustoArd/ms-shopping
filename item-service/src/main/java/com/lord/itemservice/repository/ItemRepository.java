package com.lord.itemservice.repository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.lord.itemservice.model.Item;

public interface ItemRepository extends MongoRepository<Item, ObjectId>{

	public Optional<Item> findByItemSku(String itemSku);
	
	public List<Item> findAllByProductId(Long id);
}
