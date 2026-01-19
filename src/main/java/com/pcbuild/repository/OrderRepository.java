package com.pcbuild.repository;

import com.pcbuild.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findByOrderDateAfter(LocalDateTime date);

    List<Order> findTop5ByOrderByOrderDateDesc();

    List<Order> findByUserId(String userId);
}
