package com.pcbuild.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcbuild.model.User;
import com.pcbuild.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("EMAIL_ALREADY_EXISTS");
        }

        return userRepository.save(user);
    }

    public Optional<User> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }

    public User updateProfile(String email, User updated) {
        User user = userRepository.findByEmail(email).orElseThrow();

        user.setFirstName(updated.getFirstName());
        user.setEmail(updated.getEmail());
        user.setPhone(updated.getPhone());
        user.setDob(updated.getDob());
        user.setGender(updated.getGender());
        user.setGst(updated.getGst());

        return userRepository.save(user);
    }
    
    
    
}
