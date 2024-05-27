package com.example.uade.tpo.ecommerce.entities;

import java.sql.Blob;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
public class Artwork {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  private User artist;

  @ManyToMany
  @JoinTable
  private Set<Category> categories;

  @Column
  private String description;

  @Column
  private Blob image;

  @Column
  private double price;

  @Column
  private String title;

  @Column
  private int stock;

  @Column
  private boolean hidden;

  // @Column
  // private Orientation orientation;

  // @Column
  // private Set<Style> styles;

  // @Column
  // private Set<Theme> themes;
}
