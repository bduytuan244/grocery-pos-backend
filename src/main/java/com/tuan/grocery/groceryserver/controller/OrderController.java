package com.tuan.grocery.groceryserver.controller;

import com.tuan.grocery.groceryserver.model.Order;
import com.tuan.grocery.groceryserver.repository.OrderRepository;
import com.tuan.grocery.groceryserver.repository.ProductRepository; // THÊM IMPORT KHO HÀNG
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

    // Kéo thêm ProductRepository vào để có quyền sửa số lượng sản phẩm
    @Autowired
    private ProductRepository productRepository;

    // 1. Hàm nhận hóa đơn từ Flutter gửi lên và lưu vào DB
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        // Tự động gắn thời gian hiện tại vào hóa đơn
        order.setOrderDate(LocalDateTime.now());

        // --- BẮT ĐẦU LOGIC AI THỐNG KÊ ---
        if (order.getItems() != null) {
            // Quét từng món hàng (OrderItem) có trong tờ hóa đơn
            for (com.tuan.grocery.groceryserver.model.OrderItem item : order.getItems()) {

                // Cầm ID của món hàng chạy vào kho tìm kiếm
                productRepository.findById(item.getProductId()).ifPresent(product -> {

                    // 1. CỘNG DỒN DOANH SỐ (SoldCount)
                    int currentSold = (product.getSoldCount() != null) ? product.getSoldCount() : 0;
                    product.setSoldCount(currentSold + item.getQuantity());

                    // 2. TRỪ ĐI TỒN KHO (Stock)
                    int currentStock = (product.getStock() != null) ? product.getStock() : 0;
                    int newStock = currentStock - item.getQuantity();

                    // Hàm Math.max giúp chống âm kho: Nếu newStock < 0 thì lấy số 0
                    product.setStock(Math.max(newStock, 0));

                    // Lưu món hàng đã cập nhật số liệu xuống lại MongoDB
                    productRepository.save(product);
                });
            }
        }
        // --- KẾT THÚC LOGIC AI THỐNG KÊ ---

        // Cuối cùng: Lưu nguyên tờ hóa đơn vào "Sổ đỏ"
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.ok(savedOrder);
    }

    // 2. Hàm lấy danh sách hóa đơn (Để sau này bạn làm màn hình Thống kê)
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }
}