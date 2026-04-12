package com.tuan.grocery.groceryserver.repository;

import com.tuan.grocery.groceryserver.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}