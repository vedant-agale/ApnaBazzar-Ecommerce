package com.vedant.apna_bazaar.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName; // Abhi ke liye manually "Vedant" dalenge
    private Double totalAmount;
    private String status; // "Placed", "Shipped"
    private LocalDateTime orderDate; // Kis din order hua
}