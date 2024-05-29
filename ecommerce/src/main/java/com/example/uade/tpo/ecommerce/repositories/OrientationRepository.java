package com.example.uade.tpo.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.uade.tpo.ecommerce.entities.Orientation;

import java.util.Optional;

@Repository
public interface OrientationRepository extends JpaRepository<Orientation, Long> {
  @Query(value = "select o from Orientation o where o.name = ?1")
  public Optional<Orientation> findByName(String name);
}
