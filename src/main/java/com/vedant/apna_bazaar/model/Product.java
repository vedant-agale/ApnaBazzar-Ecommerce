package com.vedant.apna_bazaar.model;

import jakarta.persistence.*;
import lombok.Data; // ✨ LOMBOK MAGIC IMPORT

@Data // 😲 Bas ye likhne se Getters/Setters apne aap ban gaye!
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String category;
    
    // Image ka URL store karenge
    private String image; 
}