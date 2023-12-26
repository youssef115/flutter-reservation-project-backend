package com.youssef.flutterProjectBackend.service;

import com.youssef.flutterProjectBackend.enties.Category;

import java.util.List;

public interface ICategoryService {
    public Category createCategory(Category c);
    public Category findCategoryById(int id);
    public Category updateCategory(Category c);
    public List<Category> findAllCategories();
    public void deleteCategory(Category c);
}
