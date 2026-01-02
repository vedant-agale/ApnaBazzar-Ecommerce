package com.vedant.apna_bazaar.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private double price;
    private int quantity;
    private String image;

    // ✨ NAYA FIELD: Ye batayega item kiska hai
    private String username; 
}