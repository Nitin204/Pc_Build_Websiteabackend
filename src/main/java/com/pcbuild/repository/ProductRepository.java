package com.pcbuild.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.pcbuild.model.Product;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByNameIgnoreCase(String name);
    void deleteByNameIgnoreCase(String name);
}
