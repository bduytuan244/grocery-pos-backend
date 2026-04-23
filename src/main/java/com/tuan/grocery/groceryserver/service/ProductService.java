package com.tuan.grocery.groceryserver.service;

import com.tuan.grocery.groceryserver.model.Product;
import com.tuan.grocery.groceryserver.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(String id, Product productDetails) {
        return productRepository.findById(id).map(product -> {
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            product.setImageUrl(productDetails.getImageUrl());

            // === BỔ SUNG 2 DÒNG NÀY ĐỂ BACKEND CHỊU LƯU DỮ LIỆU ===
            product.setCategory(productDetails.getCategory());
            product.setUnit(productDetails.getUnit());

            return productRepository.save(product);
        }).orElse(null);
    }

    public boolean deleteProduct(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}