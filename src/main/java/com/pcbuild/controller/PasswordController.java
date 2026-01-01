package com.pcbuild.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pcbuild.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class PasswordController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(
            @RequestBody Map<String, String> body) {

        Map<String, String> response = new HashMap<>();

        try {
            String email = body.get("email");

            if (email == null || email.isEmpty()) {
                response.put("message", "Email is required");
                return ResponseEntity.badRequest().body(response);
            }

            boolean exists = userRepository.findByEmail(email).isPresent();

            if (exists) {
                response.put("message", "Password reset link sent (mock)");
            } else {
                response.put("message", "Email not registered");
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace(); // VERY IMPORTANT
            response.put("message", "Server error");
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
