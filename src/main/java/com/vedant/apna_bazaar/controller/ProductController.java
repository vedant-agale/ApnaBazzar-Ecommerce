package com.vedant.apna_bazaar.controller;

import com.vedant.apna_bazaar.model.Product;
import com.vedant.apna_bazaar.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Zaroori: Taaki ye API ki tarah kaam kare
@RequestMapping("/api/products") // Zaroori: Browser isi path par data dhundega
@CrossOrigin(origins = "*") // Zaroori: Frontend (HTML) ko permission dene ke liye
public class ProductController {

    @Autowired
    private ProductRepository productRepository; // Aapka repository call

    // Purana koi bhi "GET" method ho toh usey hata kar ye simple wala rakhein
    @GetMapping
    public List<Product> getAllProducts() {
        // Ye line database ke 'products' table se saara data nikaal degi
        return productRepository.findAll();
    }
}