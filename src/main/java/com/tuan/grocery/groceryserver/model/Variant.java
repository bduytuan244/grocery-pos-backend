package com.tuan.grocery.groceryserver.model;

import lombok.Data;
import java.util.Map;

@Data
public class Variant {
    private String sku;
    private Map<String, Object> attributes;
    private Double price;
    private Integer stock;
}