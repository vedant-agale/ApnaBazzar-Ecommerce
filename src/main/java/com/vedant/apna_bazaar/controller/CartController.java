package com.vedant.apna_bazaar.controller;

import com.vedant.apna_bazaar.model.CartItem;
import com.vedant.apna_bazaar.model.Order;
import com.vedant.apna_bazaar.repository.CartRepository;
import com.vedant.apna_bazaar.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*") // Frontend se connection ke liye zaroori hai
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    // 1. ADD TO CART
    @PostMapping("/add")
    public CartItem addToCart(@RequestBody CartItem item) {
        return cartRepository.save(item);
    }

    // 2. GET USER CART
    @GetMapping("/user/{username}")
    public List<CartItem> getUserCart(@PathVariable String username) {
        return cartRepository.findByUsername(username);
    }

    // 3. REMOVE ITEM (Path ko cart.html ke hisaab se set kiya hai)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        try {
            cartRepository.deleteById(id); 
            return ResponseEntity.ok("Item removed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    // 4. CHECKOUT (Cart se data uthakar Order table mein daalna)
    @PostMapping("/checkout/{username}") // PathVariable use kiya hai
    @Transactional // Transactional zaroori hai read aur delete dono karne ke liye
    public ResponseEntity<String> checkout(@PathVariable String username) {
        
        // Step 1: User ke items nikalo
        List<CartItem> userItems = cartRepository.findByUsername(username);

        if (userItems.isEmpty()) {
            return ResponseEntity.badRequest().body("Cart is Empty!");
        }

        // Step 2: Total amount calculate karo
        double total = 0;
        for (CartItem item : userItems) {
            total += item.getPrice();
        }

        // Step 3: Order object banayein aur save karein
        Order newOrder = new Order();
        newOrder.setCustomerName(username);
        newOrder.setTotalAmount(total);
        newOrder.setStatus("Success");
        
        // ⭐ FIX: 'order' ko badal kar 'newOrder' kiya (Error resolved)
        newOrder.setOrderDate(LocalDateTime.now().toString()); 
        
        orderRepository.save(newOrder);

        // Step 4: Order ke baad user ki cart khali karein
        cartRepository.deleteAll(userItems);

        return ResponseEntity.ok("Order Placed Successfully! Order ID: " + newOrder.getId());
    }
}