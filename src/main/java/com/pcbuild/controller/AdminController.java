package com.pcbuild.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pcbuild.dto.AdminLoginRequest;
import com.pcbuild.model.Admin;
import com.pcbuild.service.AdminService;

@CrossOrigin(origins = "https://pc-build-websiteadmin.vercel.app") // Admin frontend port
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // 🔥 CREATE ADMIN (RUN ONCE)
    @PostMapping("/create")
    public ResponseEntity<?> createAdmin() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("admin123");
        return ResponseEntity.ok(adminService.createAdmin(admin));
    }

    // 🔐 ADMIN LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminLoginRequest request) {
        Optional<Admin> admin = adminService.login(request.getUsername(), request.getPassword());
        if (admin.isPresent()) {
            return ResponseEntity.ok(admin.get());
        }
        return ResponseEntity.status(401).body("Invalid Admin Credentials");
    }

    // 🔹 GET ADMIN BY USERNAME
    @GetMapping("/{username}")
    public ResponseEntity<?> getAdmin(@PathVariable String username) {
        Optional<Admin> admin = adminService.getAdminByUsername(username);
        if (admin.isPresent()) {
            // Only return username for frontend (password stays hidden)
            Admin response = new Admin();
            response.setId(admin.get().getId());
            response.setUsername(admin.get().getUsername());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(404).body("Admin not found");
    }
 // ✏️ UPDATE USERNAME
    @PutMapping("/update-username")
    public ResponseEntity<?> updateUsername(
            @RequestParam String oldUsername,
            @RequestParam String newUsername) {

        try {
            Admin updatedAdmin = adminService.updateUsername(oldUsername, newUsername);
            return ResponseEntity.ok(updatedAdmin);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // ✏️ UPDATE ADMIN PASSWORD
    @PutMapping("/update/{username}")
    public ResponseEntity<?> updateAdmin(
            @PathVariable String username,
            @RequestBody Admin admin) {

        Optional<Admin> existing = adminService.getAdminByUsername(username);
        if (existing.isPresent()) {
            Admin updatedAdmin = existing.get();
            updatedAdmin.setPassword(admin.getPassword()); // update password only
            adminService.updateAdmin(username, updatedAdmin);
            return ResponseEntity.ok(updatedAdmin);
        }
        return ResponseEntity.status(404).body("Admin not found");
    }
}
