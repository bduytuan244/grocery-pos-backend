package com.tuan.grocery.groceryserver.repository;

import com.tuan.grocery.groceryserver.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {
    // Tự động tìm tất cả danh mục và sắp xếp theo priority tăng dần
    List<Category> findAllByOrderByPriorityAsc();
}