package com.tuan.grocery.groceryserver.controller;

import com.tuan.grocery.groceryserver.model.Order;
import com.tuan.grocery.groceryserver.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    // 1. Hàm nhận hóa đơn từ Flutter gửi lên và lưu vào DB
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        // Tự động gắn thời gian hiện tại vào hóa đơn
        order.setOrderDate(LocalDateTime.now());

        // Lưu xuống MongoDB
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.ok(savedOrder);
    }

    // 2. Hàm lấy danh sách hóa đơn (Để sau này bạn làm màn hình Thống kê)
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }
}