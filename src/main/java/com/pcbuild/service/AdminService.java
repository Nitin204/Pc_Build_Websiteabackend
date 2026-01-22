package com.pcbuild.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcbuild.model.Admin;
import com.pcbuild.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // ✅ CREATE ADMIN (OPTION 3)
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // ✅ LOGIN
    public Optional<Admin> login(String username, String password) {

        Optional<Admin> adminOpt = adminRepository.findByUsername(username);

        if (adminOpt.isEmpty()) {
            System.out.println("❌ Username not found");
            return Optional.empty();
        }

        Admin admin = adminOpt.get();

        System.out.println("DB Username: " + admin.getUsername());
        System.out.println("DB Password: " + admin.getPassword());
        System.out.println("REQ Password: " + password);

        if (admin.getPassword().equals(password)) {
            System.out.println("✅ Login success");
            return Optional.of(admin);
        }

        System.out.println("❌ Password mismatch");
        return Optional.empty();
    }

}
