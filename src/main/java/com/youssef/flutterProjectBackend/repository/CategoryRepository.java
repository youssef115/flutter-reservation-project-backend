package com.youssef.flutterProjectBackend.repository;

import com.youssef.flutterProjectBackend.enties.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
