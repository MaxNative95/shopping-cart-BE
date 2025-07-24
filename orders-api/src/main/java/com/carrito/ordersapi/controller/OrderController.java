package com.carrito.ordersapi.controller;

import com.carrito.ordersapi.dto.OrderRequest;
import com.carrito.ordersapi.entity.Order;
import com.carrito.ordersapi.service.OrderService;
import com.carrito.ordersapi.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public Order createOrder(@RequestBody OrderRequest request, HttpServletRequest httpRequest) {
        Long userId = jwtUtil.extractUserId(httpRequest);
        return service.createOrder(userId, request);
    }

    @GetMapping
    public List<Order> getOrders(HttpServletRequest httpRequest) {
        Long userId = jwtUtil.extractUserId(httpRequest);
        return service.getOrders(userId);
    }
}
