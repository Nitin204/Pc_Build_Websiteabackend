package com.pcbuild.service;

import com.pcbuild.dto.*;
import com.pcbuild.model.Order;
import com.pcbuild.model.OrderItem;
import com.pcbuild.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class DashboardService {

    private final OrderRepository orderRepo;

    public DashboardService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    // 📊 SALES TREND
    public List<SalesPoint> salesTrend(String range) {

        LocalDateTime from = switch (range) {
            case "7D" -> LocalDateTime.now().minusDays(7);
            case "12M" -> LocalDateTime.now().minusMonths(12);
            default -> LocalDateTime.now().minusDays(30);
        };

        List<Order> orders = orderRepo.findByOrderDateAfter(from);

        Map<String, Double> map = new LinkedHashMap<>();

        for (Order o : orders) {
            String key = o.getOrderDate().toLocalDate().toString();
            map.put(key, map.getOrDefault(key, 0.0) + o.getTotalAmount());
        }

        List<SalesPoint> result = new ArrayList<>();
        map.forEach((k, v) -> result.add(new SalesPoint(k, v)));

        return result;
    }

    // 📦 TOP CATEGORIES
    public List<CategoryDTO> topCategories() {

        List<Order> orders = orderRepo.findAll();
        Map<String, Integer> map = new HashMap<>();

        for (Order o : orders) {
            if (o.getItems() == null) continue;

            for (OrderItem item : o.getItems()) {
                map.put(
                        item.getClass(),
                        map.getOrDefault(item.getClass(), 0) + item.getQuantity()
                );
            }
        }

        List<CategoryDTO> list = new ArrayList<>();
        map.forEach((k, v) -> list.add(new CategoryDTO(k, v)));

        return list;
    }

    // 🧾 RECENT ORDERS
    public List<Order> recentOrders() {
        return orderRepo.findTop5ByOrderByOrderDateDesc();
    }

    // 📈 STATS GRID
    public List<StatDTO> stats() {

        List<Order> orders = orderRepo.findAll();

        int totalOrders = orders.size();
        double revenue = orders.stream()
                .mapToDouble(Order::getTotalAmount)
                .sum();

        return List.of(
                new StatDTO("Total Orders", String.valueOf(totalOrders)),
                new StatDTO("Revenue", "₹ " + revenue),
                new StatDTO("New Users", "LIVE")
        );
        
    }
    
}
