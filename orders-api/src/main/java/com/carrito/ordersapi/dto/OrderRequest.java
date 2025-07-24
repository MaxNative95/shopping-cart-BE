package com.carrito.ordersapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderRequest {
    private List<OrderItemRequest> items;

}
