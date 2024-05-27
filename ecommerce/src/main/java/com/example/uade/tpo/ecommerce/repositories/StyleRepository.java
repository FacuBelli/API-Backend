package com.example.uade.tpo.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.uade.tpo.ecommerce.entities.Style;

public interface StyleRepository extends JpaRepository<Style, Long> {
  @Query(value = "select o from Style o where o.name = ?1")
  public List<Style> findByName(String name);
}
