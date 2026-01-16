package com.pcbuild.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pcbuild.model.Accessory;
import com.pcbuild.repository.AccessoryRepository;

@Service
public class AccessoryService {

    @Autowired
    private AccessoryRepository accessoryRepository;

    public Accessory addAccessory(Accessory accessory) {
        return accessoryRepository.save(accessory);
    }

    public List<Accessory> getAllAccessories() {
        return accessoryRepository.findAll();
    }

    public List<Accessory> getByCategory(String category) {
        return accessoryRepository.findByCategory(category);
    }

    public Accessory updateAccessory(String id, Accessory accessory) {
        Accessory existing = accessoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Accessory not found"));

        existing.setName(accessory.getName());
        existing.setSpecs(accessory.getSpecs());
        existing.setPrice(accessory.getPrice());
        existing.setQuantity(accessory.getQuantity());
        existing.setCategory(accessory.getCategory());
        existing.setImage(accessory.getImage());

        return accessoryRepository.save(existing);
    }

    public void deleteAccessory(String id) {
        accessoryRepository.deleteById(id);
    }
}
