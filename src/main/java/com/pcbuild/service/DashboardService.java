package com.pcbuild.service;

import com.pcbuild.dto.SalesChartDTO;
import com.pcbuild.model.OfflineOrder;
import com.pcbuild.model.Order;
import com.pcbuild.repository.OfflineOrderRepository;
import com.pcbuild.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

@Service
public class DashboardService {

    private final OrderRepository orderRepo;
    private final OfflineOrderRepository offlineRepo;

    public DashboardService(OrderRepository orderRepo,
                            OfflineOrderRepository offlineRepo) {
        this.orderRepo = orderRepo;
        this.offlineRepo = offlineRepo;
    }

    public List<SalesChartDTO> salesChart(String range) {

        boolean monthWise = "12M".equals(range);
        int days = range.equals("7D") ? 7 : range.equals("12M") ? 365 : 30;

        LocalDate fromDate = LocalDate.now().minusDays(days);

        Map<String, Double> onlineMap = new HashMap<>();
        Map<String, Double> offlineMap = new HashMap<>();

        // ===== ONLINE =====
        for (Order o : orderRepo.findAll()) {
            if (o.getOrderDate() == null) continue;

            LocalDate date = o.getOrderDate().toLocalDate();
            if (date.isBefore(fromDate)) continue;

            String key = monthWise
                    ? YearMonth.from(date).toString()
                    : date.toString();

            onlineMap.put(key,
                    onlineMap.getOrDefault(key, 0.0) + o.getTotalAmount());
        }

        // ===== OFFLINE (FIXED ✅) =====
        for (OfflineOrder o : offlineRepo.findAll()) {
            if (o.getOrderDate() == null) continue;

            LocalDate date = o.getOrderDate().toLocalDate();
            if (date.isBefore(fromDate)) continue;

            String key = monthWise
                    ? YearMonth.from(date).toString()
                    : date.toString();

            offlineMap.put(key,
                    offlineMap.getOrDefault(key, 0.0) + o.getTotal());
        }

        // ===== RESPONSE =====
        List<SalesChartDTO> result = new ArrayList<>();

        if (monthWise) {
            for (int i = 11; i >= 0; i--) {
                YearMonth ym = YearMonth.now().minusMonths(i);
                String key = ym.toString();

                result.add(new SalesChartDTO(
                        key,
                        onlineMap.getOrDefault(key, 0.0),
                        offlineMap.getOrDefault(key, 0.0)
                ));
            }
        } else {
            for (int i = days - 1; i >= 0; i--) {
                LocalDate d = LocalDate.now().minusDays(i);
                String key = d.toString();

                result.add(new SalesChartDTO(
                        key,
                        onlineMap.getOrDefault(key, 0.0),
                        offlineMap.getOrDefault(key, 0.0)
                ));
            }
        }

        return result;
    }
}
