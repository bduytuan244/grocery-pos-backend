package com.tuan.grocery.groceryserver.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "orders") // MongoDB sẽ tự tạo một bảng mới tên là "orders"
public class Order {
    @Id
    private String id;

    private List<OrderItem> items; // Danh sách các món trong giỏ
    private Double totalAmount;    // Tổng tiền
    private LocalDateTime orderDate; // Thời gian mua
}