package com.pcbuild.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pcbuild.model.Order;
import com.pcbuild.model.User;
import com.pcbuild.repository.OrderRepository;
import com.pcbuild.repository.UserRepository;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
 // ✅ PLACE ORDER (PaymentCo.jsx)
    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
    	 User user = userRepository.findById(order.getUserId()).orElse(null);

    	    if (user != null) {
    	        order.setUserName(user.getFirstName()+" "+user.getLastName());
    	        order.setUserEmail(user.getEmail());
    	    }
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("Placed");
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.ok(savedOrder);
    }


    // ✅ GET ALL ORDERS (Admin)
    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // ✅ GET ORDERS BY USER ID (Profile.jsx ke liye)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable String userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    // ✅ UPDATE ORDER STATUS (OrdersPage.jsx ke liye)
    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable String id,
            @RequestParam String status) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        orderRepository.save(order);

        return ResponseEntity.ok(order);
    }
 // ✅ DELETE ORDER
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable String id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
        return ResponseEntity.ok("Order deleted successfully");
    }

}
