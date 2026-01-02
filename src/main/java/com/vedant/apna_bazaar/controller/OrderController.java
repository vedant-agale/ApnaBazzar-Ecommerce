package com.vedant.apna_bazaar.controller;

import com.vedant.apna_bazaar.model.Order;
import com.vedant.apna_bazaar.model.CartItem;
import com.vedant.apna_bazaar.repository.OrderRepository;
import com.vedant.apna_bazaar.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*") // Zaroori: Frontend connectivity ke liye
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    // 1. PLACE ORDER: Cart se data uthakar Orders mein move karein
    @PostMapping("/place/{username}")
    @Transactional // Transactional zaroori hai kyunki hum read/write/delete ek saath kar rahe hain
    public ResponseEntity<?> placeOrder(@PathVariable String username) {
        // User ki cart se saare items nikalein
        List<CartItem> cartItems = cartRepository.findByUsername(username);

        if (cartItems.isEmpty()) {
            return ResponseEntity.badRequest().body("Cart is empty! Pehle kuch add kijiye. 🛒");
        }

        // Har item ko Order table mein save karein
        for (CartItem item : cartItems) {
            Order order = new Order();
            order.setCustomerName(username);
            order.setProductName(item.getProductName());
            order.setPrice(item.getPrice());
            order.setQuantity(item.getQuantity());
            order.setImage(item.getImage());
            order.setOrderDate(LocalDateTime.now().toString()); // Aaj ki date
            
            orderRepository.save(order);
        }

        // ⭐ Order hone ke baad Cart khali kar dein!
        cartRepository.deleteByUsername(username);

        return ResponseEntity.ok("Mubarak ho! Order place ho gaya hai. 🎁");
    }

    // 2. GET USER ORDERS: Vedant ke purane orders dikhane ke liye
    @GetMapping("/{name}")
    public List<Order> getUserOrders(@PathVariable String name) {
        return orderRepository.findByCustomerName(name);
    }
}