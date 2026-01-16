package com.pcbuild.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.pcbuild.model.CartItem;
import com.pcbuild.service.CartService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
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
        return service.addToCart(item);
    }

    @PutMapping("/qty/{id}/{qty}")
    public void updateQty(@PathVariable String id, @PathVariable int qty) {
        service.updateQty(id, qty);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.remove(id);
    }
}
