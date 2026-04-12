package com.tuan.grocery.groceryserver.model;

import lombok.Data;

@Data
public class OrderItem {
    private String productId;
    private String name;
    private Double price;
    private Integer quantity;
}