package com.vedant.apna_bazaar.controller;

import com.vedant.apna_bazaar.model.User;
import com.vedant.apna_bazaar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // --- 1. REGISTER (SIGNUP) ---
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        // Pehle check karo email already hai kya?
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "Email already exists!";
        }
        userRepository.save(user);
        return "Registration Successful! ✅";
    }

    // --- 2. LOGIN ---
    @PostMapping("/login")
    public User login(@RequestBody User loginData) {
        User user = userRepository.findByEmail(loginData.getEmail());
        
        // Agar user mila AUR password match hua
        if (user != null && user.getPassword().equals(loginData.getPassword())) {
            return user; // Pura user wapas bhej do (Frontend sambhal lega)
        }
        return null; // Galat password
    }
}