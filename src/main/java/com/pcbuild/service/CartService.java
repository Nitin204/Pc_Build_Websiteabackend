package com.pcbuild.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.pcbuild.model.CartItem;
import com.pcbuild.repository.CartRepository;
import lombok.RequiredArgsConstructor;

@Service
public class CartService {

    private final CartRepository repo;

    public CartService(CartRepository repo) {
        this.repo = repo;
    }

    public List<CartItem> getUserCart(String userId) {
        return repo.findByUserId(userId);
    }

    public CartItem addToCart(CartItem item) {
        List<CartItem> existing = repo.findByUserId(item.getUserId());

        for (CartItem c : existing) {
            if (c.getProductId().equals(item.getProductId())) {
                c.setQuantity(c.getQuantity() + 1);
                return repo.save(c);
            }
        }

        item.setQuantity(1);
        return repo.save(item);
    }

    public void updateQty(String id, int qty) {
        CartItem item = repo.findById(id).orElseThrow();
        item.setQuantity(qty);
        repo.save(item);
    }

    public void remove(String id) {
        repo.deleteById(id);
    }
}
