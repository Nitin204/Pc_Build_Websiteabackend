package com.pcbuild.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pcbuild.model.User;
import com.pcbuild.service.UserService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User savedUser = userService.register(user);
            return ResponseEntity.ok(savedUser);
        } catch (RuntimeException e) {
            if ("EMAIL_ALREADY_EXISTS".equals(e.getMessage())) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Email already registered");
            }
            return ResponseEntity.badRequest().body("Registration failed");
        }
    }


    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User request) {

        Optional<User> userOpt =
                userService.login(request.getEmail(), request.getPassword());

        if (userOpt.isEmpty()) {
            // ❌ DO NOT throw RuntimeException
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid email or password"));
        }

        User user = userOpt.get();

        // ✅ SAFE RESPONSE (NO PASSWORD)
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("id", user.getId());
        userMap.put("name", user.getFirstName());
        userMap.put("email", user.getEmail());

        Map<String, Object> response = new HashMap<>();
        response.put("token", "dummy-token"); // ok for now
        response.put("user", userMap);

        return ResponseEntity.ok(response);
    }
    
    
 // ✅ GET TOTAL USERS COUNT (Admin Dashboard)
    @GetMapping("/users/getusercount")
    public ResponseEntity<Long> getTotalUsersCount() {
        long count = userService.getTotalUsersCount();
        return ResponseEntity.ok(count);
    }

}
