package com.pcbuild.controller;


import org.springframework.web.bind.annotation.*;

import com.pcbuild.model.Product;
import com.pcbuild.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5174") // React access
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // ➕ Add Product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        if (product.getName() == null || product.getName().isBlank()) {
            throw new RuntimeException("Product name is required");
        }
        return service.addProduct(product.getName());
    }


    // ❌ Delete Product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id) {
        service.deleteProduct(id);
        return "Product deleted successfully";
    }

    // 📦 Get Products (optional but useful)
    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }
}
