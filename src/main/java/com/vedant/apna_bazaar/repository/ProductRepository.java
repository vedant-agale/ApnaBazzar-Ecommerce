package com.vedant.apna_bazaar.repository;

import com.vedant.apna_bazaar.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; // Import zaroori hai

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // ✨ Magic Method: 
    // Containing = Beech mein bhi word mile to chalega (LIKE query)
    // IgnoreCase = Chota/Bada letter matter nahi karega
    List<Product> findByNameContainingIgnoreCase(String keyword);
}