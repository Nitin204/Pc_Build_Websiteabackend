package com.pcbuild.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcbuild.model.Order;
import com.pcbuild.model.OrderItem;
import com.pcbuild.repository.CartRepository;
import com.pcbuild.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final CartRepository cartRepo;
    private final StockService stockService;

    public OrderService(
            OrderRepository orderRepo,
            CartRepository cartRepo,
            StockService stockService) {
        this.orderRepo = orderRepo;
        this.cartRepo = cartRepo;
        this.stockService = stockService;
    }

    // ✅ PLACE ORDER WITH TRANSACTION
    @Transactional
    public Order placeOrder(Order order) {
        try {
            // 1️⃣ CHECK STOCK FOR ALL ITEMS
            for (OrderItem item : order.getItems()) {
                boolean available = stockService.isStockAvailable(
                        item.getProductId(),
                        item.getQuantity(),
                        item.getProductType()
                );

                if (!available) {
                    throw new RuntimeException(
                            "Stock limit reached for " + item.getName()
                    );
                }
            }

            // 2️⃣ DEDUCT STOCK
            for (OrderItem item : order.getItems()) {
                stockService.deductStock(
                        item.getProductId(),
                        item.getQuantity(),
                        item.getProductType()
                );
            }

            // 3️⃣ SAVE ORDER
            order.setOrderDate(LocalDateTime.now());
            order.setStatus("PLACED");
            Order saved = orderRepo.save(order);

            // 4️⃣ CLEAR CART ONLY AFTER SUCCESSFUL ORDER
            cartRepo.deleteByUserId(order.getUserId());

            return saved;
            
        } catch (Exception e) {
            // Transaction will automatically rollback on exception
            throw new RuntimeException("Order placement failed: " + e.getMessage());
        }
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
    
    // Total count of the order  
    public long getTotalOrderCount() {
        return orderRepo.count();
    }
    
    // ✅ TOTAL INCOME (SUM OF ALL ORDERS)
    public double getTotalIncome() {
        return orderRepo.findAll()
                .stream()
                .mapToDouble(Order::getTotalAmount)
                .sum();
    }
}
