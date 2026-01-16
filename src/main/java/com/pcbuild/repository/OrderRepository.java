package com.pcbuild.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pcbuild.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByUserId(String userId);
    
}
