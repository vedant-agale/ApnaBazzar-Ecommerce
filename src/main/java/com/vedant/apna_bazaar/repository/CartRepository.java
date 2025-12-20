package com.vedant.apna_bazaar.repository;

import com.vedant.apna_bazaar.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartItem, Long> {
    // Manager Ready for Cart! 🛒
}