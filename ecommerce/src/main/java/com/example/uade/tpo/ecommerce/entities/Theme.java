package com.example.uade.tpo.ecommerce.entities;

import org.hibernate.annotations.DynamicUpdate;

import com.example.uade.tpo.ecommerce.dto.body.ThemeBody;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@DynamicUpdate
public class Theme {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  public Theme() {
  }

  public Theme(ThemeBody body) {
    this.name = body.getName();
  }
}
