package com.pcbuild.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.pcbuild.model.CartItem;
import com.pcbuild.repository.CartRepository;

@Service
public class CartService {

    private final CartRepository repo;
    private final StockService stockService;

    public CartService(CartRepository repo, StockService stockService) {
        this.repo = repo;
        this.stockService = stockService;
    }

    public List<CartItem> getUserCart(String userId) {
        return repo.findByUserId(userId);
    }

    public CartItem addToCart(CartItem item) {
        List<CartItem> existing = repo.findByUserId(item.getUserId());

        for (CartItem c : existing) {
            if (c.getProductId().equals(item.getProductId())) {
                int newQty = c.getQuantity() + 1;
                
                // Check stock before updating quantity
                if (!stockService.isStockAvailable(c.getProductId(), newQty, c.getProductType())) {
                    throw new RuntimeException("Stock limit reached");
                }
                
                c.setQuantity(newQty);
                return repo.save(c);
            }
        }

        // Check stock for new item
        if (!stockService.isStockAvailable(item.getProductId(), 1, item.getProductType())) {
            throw new RuntimeException("Stock limit reached");
        }

        item.setQuantity(1);
        return repo.save(item);
    }

    public void updateQty(String id, int qty) {
        CartItem item = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Cart item not found"));
        
        // Check stock before updating quantity
        if (!stockService.isStockAvailable(item.getProductId(), qty, item.getProductType())) {
            throw new RuntimeException("Stock limit reached");
        }
        
        item.setQuantity(qty);
        repo.save(item);
    }

    public void remove(String id) {
        repo.deleteById(id);
    }
}
