package com.pcbuild.controller;

import com.pcbuild.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:5173")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/sales")
    public Object sales(@RequestParam String range) {
        return dashboardService.salesTrend(range);
    }

    @GetMapping("/categories")
    public Object categories() {
        return dashboardService.topCategories();
    }

    @GetMapping("/recent-orders")
    public Object recentOrders() {
        return dashboardService.recentOrders();
    }

    @GetMapping("/stats")
    public Object stats() {
        return dashboardService.stats();
    }
}
