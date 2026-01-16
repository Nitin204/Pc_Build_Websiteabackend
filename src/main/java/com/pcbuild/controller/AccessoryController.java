package com.pcbuild.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pcbuild.model.Accessory;
import com.pcbuild.service.AccessoryService;

@CrossOrigin(origins = "http://localhost:5174") // Admin React Port
@RestController
@RequestMapping("/api/admin/accessories")
public class AccessoryController {

    @Autowired
    private AccessoryService accessoryService;

    // ➕ ADD
    @PostMapping
    public ResponseEntity<Accessory> add(@RequestBody Accessory accessory) {
        return ResponseEntity.ok(accessoryService.addAccessory(accessory));
    }

    // 📥 GET ALL
    @GetMapping
    public ResponseEntity<List<Accessory>> getAll() {
        return ResponseEntity.ok(accessoryService.getAllAccessories());
    }

    // 🔍 GET BY CATEGORY
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Accessory>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(accessoryService.getByCategory(category));
    }

    // ✏️ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Accessory> update(
            @PathVariable String id,
            @RequestBody Accessory accessory) {
        return ResponseEntity.ok(accessoryService.updateAccessory(id, accessory));
    }

    // ❌ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        accessoryService.deleteAccessory(id);
        return ResponseEntity.ok("Accessory deleted");
    }
}
