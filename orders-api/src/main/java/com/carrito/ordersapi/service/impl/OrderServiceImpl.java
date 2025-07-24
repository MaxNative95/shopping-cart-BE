package com.carrito.ordersapi.service.impl;

import com.carrito.ordersapi.dto.OrderItemRequest;
import com.carrito.ordersapi.dto.OrderRequest;
import com.carrito.ordersapi.entity.Order;
import com.carrito.ordersapi.entity.OrderItem;
import com.carrito.ordersapi.repository.OrderRepository;
import com.carrito.ordersapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Long userId, OrderRequest request) {
        Order order = new Order();
        order.setUserId(userId);

        List<OrderItem> items = request.getItems().stream().map(itemReq -> {
            OrderItem item = new OrderItem();
            item.setProductId(itemReq.getId());
            item.setQuantity(itemReq.getQuantity());
            item.setOrder(order);
            return item;
        }).collect(Collectors.toList());

        order.setItems(items);
        order.setTotal(items.stream().mapToDouble(i -> 0).sum());

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
