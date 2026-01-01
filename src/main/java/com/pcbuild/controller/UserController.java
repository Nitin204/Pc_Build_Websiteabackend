package com.pcbuild.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pcbuild.model.User;
import com.pcbuild.service.UserService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/update/{email}")
    public User updateProfile(@PathVariable String email, @RequestBody User user) {
        return userService.updateProfile(email, user);
    }
}
