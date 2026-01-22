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

    // CREATE ADMIN
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // LOGIN
    public Optional<Admin> login(String username, String password) {
        Optional<Admin> admin = adminRepository.findByUsername(username);

        if (admin.isPresent() && admin.get().getPassword().equals(password)) {
            return admin;
        }
        return Optional.empty();
    }

    // GET ADMIN
    public Optional<Admin> getByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
}
