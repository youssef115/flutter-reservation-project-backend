package com.youssef.flutterProjectBackend.controller;

import com.youssef.flutterProjectBackend.enties.Category;
import com.youssef.flutterProjectBackend.models.ResponseModel;
import com.youssef.flutterProjectBackend.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseModel<List<Category>> getAll(){
        return new ResponseModel<>(HttpStatus.OK.value(), "list of all category ",categoryService.findAllCategories());
    }
    @GetMapping("{id}")
    public ResponseModel<Category>  getCategory(@PathVariable int id ){
        return new ResponseModel<>(HttpStatus.OK.value(),"get the category by id successfully",categoryService.findCategoryById(id));
    }
    @PostMapping
    public ResponseModel<Category>  addCategory(@RequestBody Category category){
        return new ResponseModel<>(HttpStatus.OK.value(), "add new category successfully",categoryService.createCategory(category));
    }
    @PutMapping
    public ResponseModel<Category> updateCategory(@RequestBody Category category){
        Category updateCategory=categoryService.findCategoryById(category.getId());
        updateCategory.setTitle(category.getTitle());
        return new ResponseModel<>(HttpStatus.OK.value(), "the category is updated successfully",categoryService.updateCategory(updateCategory));
    }

    @DeleteMapping("{id}")
    public ResponseModel<Category> deleteCategory (@PathVariable int id){
        Category category=categoryService.findCategoryById(id);
        categoryService.deleteCategory(category);
        return new ResponseModel<>(HttpStatus.OK.value(),"the Category is deleted successfully",category );
    }
}
