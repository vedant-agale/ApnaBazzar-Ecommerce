package com.vedant.apna_bazaar.controller;

import com.vedant.apna_bazaar.model.CartItem;
import com.vedant.apna_bazaar.model.Order;
import com.vedant.apna_bazaar.repository.CartRepository;
import com.vedant.apna_bazaar.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    // --- 1. ADD TO CART (Ye gayab tha!) ---
    @PostMapping("/add")
    public CartItem addToCart(@RequestBody CartItem item) {
        return cartRepository.save(item);
    }

    // --- 2. SHOW ALL CART ITEMS (Ye bhi gayab tha!) ---
    @GetMapping("/all")
    public List<CartItem> getCartItems() {
        return cartRepository.findAll();
    }

    // --- 4. REMOVE ITEM (Delete Jugad) ---
    @DeleteMapping("/remove/{id}") // DELETE request aayegi ID ke sath
    public String removeFromCart(@PathVariable Long id) {
        cartRepository.deleteById(id);
        return "Item Hataya Gaya!";
    }

    // --- 3. CHECKOUT API (Naya wala) ---
    @PostMapping("/checkout")
    public String checkout(@RequestParam String name) {
        // 1. Cart ka maal nikalo
        List<CartItem> items = cartRepository.findAll();

        if(items.isEmpty()) {
            return "Cart is Empty!";
        }

        // 2. Total calculate karo
        double total = 0;
        for(CartItem item : items) {
            total += item.getPrice();
        }

        // 3. Order save karo
        Order newOrder = new Order();
        newOrder.setCustomerName(name);
        newOrder.setTotalAmount(total);
        newOrder.setStatus("Success");
        newOrder.setOrderDate(LocalDateTime.now());
        
        orderRepository.save(newOrder);

        // 4. Cart khali karo (Flush)
        cartRepository.deleteAll();

        return "Order Placed Successfully! Order ID: " + newOrder.getId();
    }
}