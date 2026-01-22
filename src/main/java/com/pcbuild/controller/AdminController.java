package com.pcbuild.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pcbuild.dto.AdminLoginRequest;
import com.pcbuild.model.Admin;
import com.pcbuild.service.AdminService;

@CrossOrigin(origins = "https://pc-build-websiteadmin.vercel.app") // ✅ safe for Render
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // 🔥 CREATE ADMIN (RUN ONCE)
    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.createAdmin(admin));
    }

    // 🔐 ADMIN LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminLoginRequest request) {
        Optional<Admin> admin =
                adminService.login(request.getUsername(), request.getPassword());

        if (admin.isPresent()) {
            return ResponseEntity.ok(admin.get());
        }
        return ResponseEntity.status(401).body("Invalid Admin Credentials");
    }

    // 🔍 GET ADMIN
    @GetMapping("/{username}")
    public ResponseEntity<?> getAdmin(@PathVariable String username) {
        return adminService.getByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
