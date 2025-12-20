package com.vedant.apna_bazaar.repository;

import com.vedant.apna_bazaar.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; // Ye import zaroor karna

public interface OrderRepository extends JpaRepository<Order, Long> {
    
    // ✨ Magic Method: Spring Boot padhke samajh jayega ki "CustomerName" wala column dhundna hai
    List<Order> findByCustomerName(String customerName);
}