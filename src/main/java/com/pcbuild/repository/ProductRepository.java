package com.pcbuild.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.pcbuild.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    boolean existsByName(String name);
}
