//Capa de trafico: se implementan los diferentes endpoints. Aca se ponen el get, post, put y demas. Entonces asi modularizas y solo tiene una responsabilidad, no hay logica de negocio, solo CRUD

package com.example.uade.tpo.ecommerce.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.uade.tpo.ecommerce.entity.Category;
import com.example.uade.tpo.ecommerce.entity.dto.CategoryRequest;
import com.example.uade.tpo.ecommerce.exceptions.CategoryDuplicateException;
import com.example.uade.tpo.ecommerce.service.CategoryService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




//Con esto declaro que esta clase es un endpoint HTTP
@RestController 


// Esto me define que url tiene que apuntar, a cada url que empiece con categoires, me trae todo lo que tiene ahi
@RequestMapping("categories") 





public class CategoryController {

    @Autowired //Averiguar que hace esto
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
            throws CategoryDuplicateException {
        Category result = categoryService.createCategory(categoryRequest.getDescription());
        return ResponseEntity.created(URI.create("/categories/" + result.getId())).body(result);
    }
    
    
}
