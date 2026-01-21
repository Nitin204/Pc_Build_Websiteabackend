package com.pcbuild.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcbuild.model.Accessory;
import com.pcbuild.model.PCBuild;
import com.pcbuild.model.PreBuilt;
import com.pcbuild.model.ProductType;
import com.pcbuild.repository.AccessoryRepository;
import com.pcbuild.repository.PCBuildRepository;
import com.pcbuild.repository.PreBuiltRepository;

@Service
public class StockService {

    @Autowired
    private AccessoryRepository accessoryRepo;

    @Autowired
    private PreBuiltRepository preBuiltRepo;

    @Autowired
    private PCBuildRepository pcBuildRepo;

    // ✅ CHECK STOCK
    public boolean isStockAvailable(String productId, int qty, ProductType type) {
        switch (type) {
            case ACCESSORY:
                return accessoryRepo.findById(productId)
                        .map(p -> p.getQuantity() >= qty)
                        .orElse(false);

            case PREBUILT:
                return preBuiltRepo.findById(productId)
                        .map(p -> p.getQuantity() >= qty)
                        .orElse(false);

            case PCBUILD:
                return pcBuildRepo.findById(productId)
                        .map(p -> p.getQuantity() >= qty)
                        .orElse(false);

            default:
                return false;
        }
    }

    // ✅ DEDUCT STOCK
    public void deductStock(String productId, int qty, ProductType type) {
        switch (type) {
            case ACCESSORY:
                Accessory a = accessoryRepo.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Accessory not found"));
                if (a.getQuantity() < qty) {
                    throw new RuntimeException("Stock limit reached for " + a.getName());
                }
                a.setQuantity(a.getQuantity() - qty);
                accessoryRepo.save(a);
                break;

            case PREBUILT:
                PreBuilt p = preBuiltRepo.findById(productId)
                    .orElseThrow(() -> new RuntimeException("PreBuilt not found"));
                if (p.getQuantity() < qty) {
                    throw new RuntimeException("Stock limit reached for " + p.getName());
                }
                p.setQuantity(p.getQuantity() - qty);
                preBuiltRepo.save(p);
                break;

            case PCBUILD:
                PCBuild pc = pcBuildRepo.findById(productId)
                    .orElseThrow(() -> new RuntimeException("PCBuild not found"));
                if (pc.getQuantity() < qty) {
                    throw new RuntimeException("Stock limit reached for " + pc.getName());
                }
                pc.setQuantity(pc.getQuantity() - qty);
                pcBuildRepo.save(pc);
                break;

            default:
                throw new RuntimeException("Invalid product type");
        }
    }
}
