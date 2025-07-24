package com.carrito.ordersapi.service;

import com.carrito.ordersapi.dto.OrderRequest;
import com.carrito.ordersapi.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Long userId, OrderRequest request);
    List<Order> getOrders(Long userId);
}
