package com.vedant.apna_bazaar.repository;

import com.vedant.apna_bazaar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 🔑 YE LINE HONI ZAROORI HAI:
    User findByEmail(String email); 
}