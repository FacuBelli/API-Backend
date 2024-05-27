package com.example.uade.tpo.ecommerce.entities;

import com.example.uade.tpo.ecommerce.dto.CategoryBody;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  public Category() {
  }

  public Category(CategoryBody body) {
    this.name = body.getName();
  }
}
