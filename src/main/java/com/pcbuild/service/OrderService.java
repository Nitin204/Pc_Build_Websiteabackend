package com.pcbuild.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

import com.pcbuild.model.Order;
import com.pcbuild.repository.CartRepository;
import com.pcbuild.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final CartRepository cartRepo;

    public OrderService(OrderRepository orderRepo, CartRepository cartRepo) {
        this.orderRepo = orderRepo;
        this.cartRepo = cartRepo;
    }

    // ✅ PLACE ORDER
    public String placeOrder(Order order) {

        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");

        Order saved = orderRepo.save(order);

        // clear cart
        cartRepo.deleteByUserId(order.getUserId());

        return saved.getId();
    }

    // ✅ USER ORDERS
    public List<Order> getOrdersByUser(String userId) {
        return orderRepo.findByUserId(userId);
    }

    // ✅ ALL ORDERS (ADMIN)
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    // ✅ UPDATE STATUS (ADMIN)
    public void updateOrderStatus(String orderId, String status) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        orderRepo.save(order);
    }
    public List<Order> getOrdersByUserId(String userId) {
        return orderRepo.findByUserId(userId);
    }
    
//    Totoal count of the order  

    public long getTotalOrderCount() {
        return orderRepo.count();
    }
    
    
 // ✅ TOTAL INCOME (SUM OF ALL ORDERS)
    public double getTotalIncome() {
        return orderRepo.findAll()
                .stream()
                .mapToDouble(Order::getTotalAmount) // change field name if needed
                .sum();
    }

}
