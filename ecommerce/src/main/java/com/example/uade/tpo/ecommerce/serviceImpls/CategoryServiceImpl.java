package com.example.uade.tpo.ecommerce.serviceImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.ecommerce.dto.body.CategoryBody;
import com.example.uade.tpo.ecommerce.entities.Category;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.repositories.CategoryRepository;
import com.example.uade.tpo.ecommerce.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  public List<Category> getCategories() {
    return categoryRepository.findAll();
  }

  public Optional<Category> getCategoryById(Long id) {
    return categoryRepository.findById(id);
  }

  public Optional<Category> getCategoryByName(String name) {
    return categoryRepository.findByName(name);
  }

  public Category createCategory(CategoryBody body) throws DuplicateException {
    Optional<Category> category = categoryRepository.findByName(body.getName());
    if (category.isPresent())
      throw new DuplicateException("La Category ya existe.");
    return categoryRepository.save(new Category(body));
  }
}
