package com.example.uade.tpo.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.uade.tpo.ecommerce.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  @Query(value = "select a from Artwork a where a.artist = ?1")
  public List<User> findByUserId(Long id);
}