package com.pcbuild.service;

import org.springframework.stereotype.Service;
import com.pcbuild.model.OfflineOrder;
import com.pcbuild.repository.OfflineOrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OfflineOrderService {

    private final OfflineOrderRepository repo;

    public OfflineOrderService(OfflineOrderRepository repo) {
        this.repo = repo;
    }

    public OfflineOrder saveOrder(OfflineOrder order) {
        if (order.getOrderDate() == null) {
            order.setOrderDate(LocalDateTime.now()); // ✅ auto date
        }
        return repo.save(order);
    }

    public List<OfflineOrder> getAllOrders() {
        return repo.findAll();
    }

    public void deleteOrder(String id) {
        repo.deleteById(id);
    }

    public OfflineOrder updateOrder(String id, OfflineOrder updatedOrder) {
        OfflineOrder existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Offline order not found"));

        existing.setCustomer(updatedOrder.getCustomer());
        existing.setEmail(updatedOrder.getEmail());
        existing.setProduct(updatedOrder.getProduct());
        existing.setQty(updatedOrder.getQty());
        existing.setAmount(updatedOrder.getAmount());
        existing.setDiscount(updatedOrder.getDiscount());
        existing.setGst(updatedOrder.getGst());
        existing.setTotal(updatedOrder.getTotal());
        existing.setPayment(updatedOrder.getPayment());
        existing.setOrderDate(updatedOrder.getOrderDate());

        return repo.save(existing);
    }

    public Double getTotalOfflineRevenue() {
        Double total = repo.getTotalOfflineRevenue();
        return total != null ? total : 0.0;
    }
}
