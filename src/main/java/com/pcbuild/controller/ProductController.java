package com.pcbuild.controller;

import org.springframework.web.bind.annotation.*;
import com.pcbuild.model.Product;
import com.pcbuild.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5174")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getProducts() {
        return service.getAllProducts();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return service.addProduct(product);
    }

    @DeleteMapping("/{name}")
    public void deleteProduct(@PathVariable String name) {
        service.deleteProduct(name);
    }
}
