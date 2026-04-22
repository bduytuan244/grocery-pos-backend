package com.tuan.grocery.groceryserver.controller;

import com.tuan.grocery.groceryserver.model.Category;
import com.tuan.grocery.groceryserver.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // API lấy danh sách đã được sắp xếp sẵn
    @GetMapping
    public List<Category> getCategories() {
        return categoryRepository.findAllByOrderByPriorityAsc();
    }

    // API để thêm danh mục mới
    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }
}