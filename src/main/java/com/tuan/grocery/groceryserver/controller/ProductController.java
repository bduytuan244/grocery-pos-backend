package com.tuan.grocery.groceryserver.controller;

import com.tuan.grocery.groceryserver.model.Product;
import com.tuan.grocery.groceryserver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.addProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product productDetails) {
        // Gọi xuống tầng Service để xử lý logic update
        Product updatedProduct = productService.updateProduct(id, productDetails);

        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build(); // Trả về lỗi 404 nếu không tìm thấy ID
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        // Gọi xuống tầng Service để xóa
        boolean isDeleted = productService.deleteProduct(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build(); // Trả về mã 204 (Xóa thành công, không cần data trả về)
        } else {
            return ResponseEntity.notFound().build(); // Trả về lỗi 404 nếu không tìm thấy ID
        }
    }
}