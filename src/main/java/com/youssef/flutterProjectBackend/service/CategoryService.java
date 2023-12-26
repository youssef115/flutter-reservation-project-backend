package com.youssef.flutterProjectBackend.service;

import com.youssef.flutterProjectBackend.enties.Category;
import com.youssef.flutterProjectBackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category createCategory(Category c) {
        return categoryRepository.save(c);
    }

    @Override
    public Category findCategoryById(int id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category updateCategory(Category c) {
        return categoryRepository.save(c);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(Category c) {
        categoryRepository.delete(c);
    }
}
