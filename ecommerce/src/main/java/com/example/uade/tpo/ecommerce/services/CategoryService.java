package com.example.uade.tpo.ecommerce.services;

import java.util.List;
import java.util.Optional;

import com.example.uade.tpo.ecommerce.entities.Category;
import com.example.uade.tpo.ecommerce.exceptions.CategoryDuplicateException;



public interface CategoryService {

    public List<Category> getCategories();
    
    public Optional<Category> getCategoryById(Long categoryId);
    
    public Category createCategory(String name) throws CategoryDuplicateException;
}
