package com.vedant.apna_bazaar.controller;

import com.vedant.apna_bazaar.model.Order;
import com.vedant.apna_bazaar.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    // API: Vedant ke saare orders laao
    // URL: /api/orders/Vedant
    @GetMapping("/{name}")
    public List<Order> getUserOrders(@PathVariable String name) {
        return orderRepository.findByCustomerName(name);
    }
}