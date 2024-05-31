//Capa de trafico: se implementan los diferentes endpoints. Aca se ponen el get, post, put y demas. Entonces asi modularizas y solo tiene una responsabilidad, no hay logica de negocio, solo CRUD

package com.example.uade.tpo.ecommerce.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.uade.tpo.ecommerce.dto.body.CategoryBody;
import com.example.uade.tpo.ecommerce.dto.request.CategoryRequest;
import com.example.uade.tpo.ecommerce.entities.Category;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.services.CategoryService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

//Con esto declaro que esta clase es un endpoint HTTP
@RestController
// Esto me define que url tiene que apuntar, a cada url que empiece con
// categories, me trae todo lo que tiene ahi
@RequestMapping("category")
public class CategoryController {
  @Autowired // Averiguar que hace esto
  private CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<Category>> getCategories() {
    return ResponseEntity.ok(categoryService.getCategories());
  }

  @GetMapping("/{categoryId}")
  public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
    Optional<Category> result = categoryService.getCategoryById(categoryId);
    if (result.isPresent())
      return ResponseEntity.ok(result.get());
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<Object> createCategory(@RequestBody CategoryRequest categoryRequest)
      throws DuplicateException {
    CategoryBody body = CategoryBody.builder().name(categoryRequest.getName()).build();
    Category result = categoryService.createCategory(body);
    return ResponseEntity.created(URI.create("/category/" + result.getId())).body(result);
  }

  @DeleteMapping ("/{categoryId}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId){
    boolean deleted = categoryService.deleteCategory(categoryId);
    if (deleted){
      return ResponseEntity.noContent().build();
    }else{
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping ("/{categoryId}")
  public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequest categoryRequest){
    CategoryBody body = CategoryBody.builder().name(categoryRequest.getName()).build();
    Optional<Category> updatedCategory = categoryService.updateCategory(categoryId,body);

    if(updatedCategory.isPresent()){
      return ResponseEntity.ok(updatedCategory.get());
    }else{
      return ResponseEntity.notFound().build();
    }
  }
}
