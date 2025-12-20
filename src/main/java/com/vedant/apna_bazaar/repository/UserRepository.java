package com.vedant.apna_bazaar.repository;

import com.vedant.apna_bazaar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
    // ✨ Magic Method: Login ke waqt kaam aayega
    // "Select * from users where email = ?"
    User findByEmail(String email);
}