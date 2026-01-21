package com.pcbuild.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.pcbuild.model.CartItem;
import com.pcbuild.service.CartService;

@RestController
@CrossOrigin(origins = "https://pc-build-website.vercel.app")
@RequestMapping("/api/cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable String userId) {
        return service.getUserCart(userId);
    }

    @PostMapping("/add")
    public CartItem add(@RequestBody CartItem item) {
        try {
            return service.addToCart(item);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/qty/{id}/{qty}")
    public void updateQty(@PathVariable String id, @PathVariable int qty) {
        try {
            service.updateQty(id, qty);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.remove(id);
    }
}
