package com.example.uade.tpo.ecommerce.service;


import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;

import com.example.uade.tpo.ecommerce.entity.Category;
import com.example.uade.tpo.ecommerce.exceptions.CategoryDuplicateException;
import com.example.uade.tpo.ecommerce.repository.CategoryRepository;

public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public Category createCategory(String description) throws CategoryDuplicateException {
        List<Category> categories = categoryRepository.findByDescription(description);
        if (categories.isEmpty())
        return categoryRepository.save(new Category(description));
                
        throw new CategoryDuplicateException();
        
    }
}
