package com.example.uade.tpo.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.uade.tpo.ecommerce.entity.Category;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository <Category, Long>{
    @Query(value = "select c from Category c where c.description = ?1")//Se puede escribir en SQL o JPA
    List<Category>findByDescription(String description);
    
}
