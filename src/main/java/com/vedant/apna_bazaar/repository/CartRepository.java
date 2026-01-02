package com.vedant.apna_bazaar.repository;

import com.vedant.apna_bazaar.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface CartRepository extends JpaRepository<CartItem, Long> {
    
    // 🔍 1. Username ke hisaab se items dhundo (Isse cart load hoti hai)
    List<CartItem> findByUsername(String username);
    
    // 🗑️ 2. MAGIC METHOD: Order hone ke baad cart khali karne ke liye
    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem c WHERE c.username = ?1")
    void deleteByUsername(String username);
}