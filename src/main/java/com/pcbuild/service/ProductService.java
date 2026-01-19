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

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product addProduct(Product product) {

        if (product.getName() == null || product.getName().isBlank()) {
            throw new RuntimeException("Product name required");
        }

        repo.findByNameIgnoreCase(product.getName())
            .ifPresent(p -> {
                throw new RuntimeException("Product already exists");
            });

        return repo.save(product);
    }

    public void deleteProduct(String name) {
        repo.findByNameIgnoreCase(name)
            .orElseThrow(() -> new RuntimeException("Product not found"));

        repo.deleteByNameIgnoreCase(name);
    }
}
