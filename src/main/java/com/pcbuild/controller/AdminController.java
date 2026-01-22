package com.pcbuild.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pcbuild.dto.AdminLoginRequest;
import com.pcbuild.model.Admin;
import com.pcbuild.service.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "https://pc-build-websiteadmin.vercel.app")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminLoginRequest request) {

        Optional<Admin> adminOpt =
                adminService.login(request.getUsername(), request.getPassword());

        if (adminOpt.isPresent()) {
            return ResponseEntity.ok(adminOpt.get());
        }

        return ResponseEntity.status(401).body("Invalid Admin Credentials");
    }
}
