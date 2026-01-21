package com.pcbuild.controller;

import org.springframework.web.bind.annotation.*;
import com.pcbuild.model.OfflineOrder;
import com.pcbuild.service.OfflineOrderService;

import java.util.List;

@RestController
@RequestMapping("/api/offline-orders")
@CrossOrigin(origins = "http://localhost:5174")
public class OfflineOrderController {

    private final OfflineOrderService service;

    public OfflineOrderController(OfflineOrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<OfflineOrder> getOrders() {
        return service.getAllOrders();
    }

    @PostMapping
    public OfflineOrder saveOrder(@RequestBody OfflineOrder order) {
        return service.saveOrder(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable String id) {
        service.deleteOrder(id);
    }

    @PutMapping("/{id}")
    public OfflineOrder updateOrder(
            @PathVariable String id,
            @RequestBody OfflineOrder order) {
        return service.updateOrder(id, order);
    }

    // ✅ TOTAL OFFLINE REVENUE
    @GetMapping("/revenue")
    public Double getTotalOfflineRevenue() {
        return service.getTotalOfflineRevenue();
    }
}
