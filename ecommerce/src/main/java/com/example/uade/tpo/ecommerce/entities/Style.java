package com.example.uade.tpo.ecommerce.entities;

import com.example.uade.tpo.ecommerce.dto.body.StyleBody;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Style {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  public Style() {
  }

  public Style(StyleBody body) {
    this.name = body.getName();
  }
}
