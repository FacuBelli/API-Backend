package com.example.uade.tpo.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.uade.tpo.ecommerce.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  @Query(value = "select u from User u where u.email = ?1")
  public Optional<User> findByEmail(String email);
}
