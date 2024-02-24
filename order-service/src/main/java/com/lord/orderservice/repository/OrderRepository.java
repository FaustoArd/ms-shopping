package com.lord.orderservice.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.lord.orderservice.model.Order;

public interface OrderRepository extends MongoRepository<Order,ObjectId>{

}
