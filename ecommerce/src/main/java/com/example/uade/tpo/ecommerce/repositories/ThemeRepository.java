package com.example.uade.tpo.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.uade.tpo.ecommerce.entities.Theme;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
  @Query(value = "select a from Theme a where a.name = ?1")
  public Optional<Theme> findByName(String name);
}
