package com.tuan.grocery.groceryserver.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    private String name;
    private String category;
    private List<String> keywords;
    private String unit;

    private boolean hasVariants;

    private Double price;
    private Integer stock;
    private String imageUrl;
    private List<Variant> variants;
    private Integer soldCount = 0;   // Số lượng đã bán ra
    private Integer searchCount = 0; // Số lần khách bấm vào/tìm kiếm
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}