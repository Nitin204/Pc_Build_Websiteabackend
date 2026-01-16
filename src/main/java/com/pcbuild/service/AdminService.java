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
        Optional<Admin> admin = adminRepository.findByUsername(username);

        if (admin.isPresent() && admin.get().getPassword().equals(password)) {
            return admin;
        }
        return Optional.empty();
    }

    // ✅ UPDATE ADMIN
    public Admin updateAdmin(String username, Admin updatedAdmin) {

        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        admin.setUsername(updatedAdmin.getUsername());
        admin.setPassword(updatedAdmin.getPassword());

        return adminRepository.save(admin);
    }
    public Admin updateUsername(String oldUsername, String newUsername) {

        // check old username exists
        Admin admin = adminRepository.findByUsername(oldUsername)
                .orElseThrow(() -> new RuntimeException("Old username not found"));

        // check new username already exists
        if (adminRepository.findByUsername(newUsername).isPresent()) {
            throw new RuntimeException("Username already taken");
        }

        admin.setUsername(newUsername);
        return adminRepository.save(admin);
    }
    
    
    public Optional<Admin> getAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

}
