package com.vedant.apna_bazaar.controller;

import com.vedant.apna_bazaar.model.User;
import com.vedant.apna_bazaar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Zaroori: Taaki browser login ko block na kare
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // 📝 1. REGISTER (Naya Account Banane ke liye)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }

    // 🔑 2. LOGIN (Check karne ke liye)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginData) {
        // Database mein email se user ko dhundo
        User user = userRepository.findByEmail(loginData.getEmail());

        if (user != null && user.getPassword().equals(loginData.getPassword())) {
            return ResponseEntity.ok(user); // Agar password sahi hai toh user details bhej do
        } else {
            return ResponseEntity.status(401).body("Invalid Email or Password");
        }
    }
}