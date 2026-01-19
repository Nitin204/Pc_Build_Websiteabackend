package com.pcbuild.service;


import org.springframework.stereotype.Service;

import com.pcbuild.model.Product;
import com.pcbuild.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Product addProduct(String name) {
        if (repo.existsByName(name)) {
            throw new RuntimeException("Product already exists");
        }

        Product product = new Product();
        product.setName(name);   // ✅ SET NAME

        return repo.save(product);
    }


    public void deleteProduct(String id) {
        repo.deleteById(id);
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }
}
