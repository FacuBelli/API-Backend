package com.example.uade.tpo.ecommerce.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.uade.tpo.ecommerce.dto.body.CategoryBody;
import com.example.uade.tpo.ecommerce.dto.request.CategoryRequest;
import com.example.uade.tpo.ecommerce.entities.Category;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.exceptions.NotFoundException;
import com.example.uade.tpo.ecommerce.services.CategoryService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("category")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<Category>> getCategories() {
    return ResponseEntity.ok(categoryService.getCategories());
  }

  @GetMapping("/{categoryId}")
  public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) throws NotFoundException {
    Optional<Category> category = categoryService.getCategoryById(categoryId);
    if (!category.isPresent()) {
      throw new NotFoundException("La Category(id): " + categoryId + " no existe.");
    }

    return ResponseEntity.ok(category.get());
  }

  @PostMapping
  public ResponseEntity<Category> createCategory(@RequestBody CategoryRequest categoryRequest)
      throws DuplicateException {
    CategoryBody body = CategoryBody.builder().name(categoryRequest.getName()).build();
    Category category = categoryService.createCategory(body);
    return ResponseEntity.created(URI.create("/category/" + category.getId())).body(category);
  }

  @PutMapping("/{categoryId}")
  public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId,
      @RequestBody CategoryRequest categoryRequest) throws NotFoundException {
    Optional<Category> category = categoryService.getCategoryById(categoryId);
    if (!category.isPresent()) {
      throw new NotFoundException("La Category(id): " + categoryId + " no existe.");
    }

    CategoryBody body = CategoryBody.builder().name(categoryRequest.getName()).build();
    Category newCategory = categoryService.updateCategory(category.get(), body);
    return ResponseEntity.ok(newCategory);
  }

  @DeleteMapping("/{categoryId}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) throws NotFoundException {
    Optional<Category> category = categoryService.getCategoryById(categoryId);
    if (!category.isPresent()) {
      throw new NotFoundException("La Category(id): " + categoryId + " no existe.");
    }

    categoryService.deleteCategory(category.get());
    return ResponseEntity.ok().build();
  }
}
