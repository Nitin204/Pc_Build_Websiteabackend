package com.pcbuild.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    // LOGIN
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User request) {
        Optional<User> user = userService.login(request.getEmail(), request.getPassword());

        if (user.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("user", user.get());
            response.put("token", "dummy-token"); // frontend expects token
            return response;
        }

        throw new RuntimeException("Invalid email or password");
    }
}
