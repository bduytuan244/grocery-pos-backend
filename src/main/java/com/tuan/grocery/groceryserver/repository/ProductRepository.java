package com.tuan.grocery.groceryserver.repository;

import com.tuan.grocery.groceryserver.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}