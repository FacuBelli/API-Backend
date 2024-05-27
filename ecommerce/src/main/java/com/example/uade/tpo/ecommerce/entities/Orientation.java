package com.example.uade.tpo.ecommerce.entities;

import com.example.uade.tpo.ecommerce.dto.OrientationBody;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Orientation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  public Orientation() {
  }

  public Orientation(OrientationBody body) {
    this.name = body.getName();
  }
}
