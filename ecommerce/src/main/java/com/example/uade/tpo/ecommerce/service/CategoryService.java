package com.example.uade.tpo.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.example.uade.tpo.ecommerce.entity.Category;
import com.example.uade.tpo.ecommerce.exceptions.CategoryDuplicateException;



public interface CategoryService {

    public List<Category> getCategories();
    
    public Optional<Category> getCategoryById(Long categoryId);
    
    public Category createCategory(String description) throws CategoryDuplicateException;
}
