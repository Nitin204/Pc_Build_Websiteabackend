package com.pcbuild.controller;

import com.pcbuild.dto.SalesChartDTO;
import com.pcbuild.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "https://pc-build-websiteadmin.vercel.app")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/sales")
    public ResponseEntity<List<SalesChartDTO>> sales(
            @RequestParam(defaultValue = "30D") String range
    ) {
        return ResponseEntity.ok(dashboardService.salesChart(range));
    }
}
