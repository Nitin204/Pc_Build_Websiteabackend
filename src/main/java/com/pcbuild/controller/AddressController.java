package com.pcbuild.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.pcbuild.model.Address;
import com.pcbuild.service.AddressService;

@RestController
@RequestMapping("/api/address")
@CrossOrigin(origins = "http://localhost:5173")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    // ➕ ADD
    @PostMapping
    public Address add(@RequestBody Address address) {
        return service.save(address);
    }

    // ✏️ UPDATE
    @PutMapping("/{id}")
    public Address update(@PathVariable String id, @RequestBody Address address) {
        address.setId(id);
        return service.save(address);
    }

    // ❌ DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    // 📍 GET USER ADDRESSES
    @GetMapping("/{userId}")
    public List<Address> get(@PathVariable String userId) {
        return service.getUserAddresses(userId);
    }
}
