package com.example.uade.tpo.ecommerce.services;

import java.util.List;
import java.util.Optional;

import com.example.uade.tpo.ecommerce.dto.body.CategoryBody;
import com.example.uade.tpo.ecommerce.entities.Category;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;

public interface CategoryService {
  public List<Category> getCategories();

  public Optional<Category> getCategoryById(Long id);

  public Optional<Category> getCategoryByName(String name);

  public Category createCategory(CategoryBody body) throws DuplicateException;

  public boolean deleteCategory(Long categoryId);

  public Optional <Category> updateCategory(Long categoryId, CategoryBody body);
}
