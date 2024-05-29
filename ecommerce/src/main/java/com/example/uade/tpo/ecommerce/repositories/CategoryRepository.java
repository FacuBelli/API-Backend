package com.example.uade.tpo.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.uade.tpo.ecommerce.entities.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  @Query(value = "select c from Category c where c.name = ?1")
  public Optional<Category> findByName(String name);
}
