package com.carrito.ordersapi.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItemRequest {
    private Long id;
    private int quantity;

}
