package com.vedant.apna_bazaar.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @Column(unique = true) // Email sabka alag hona chahiye
    private String email;
    
    private String password; // Asli project mein ise encrypt karte hain, abhi simple rakhenge
}