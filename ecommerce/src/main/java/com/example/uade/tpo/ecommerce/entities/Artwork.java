package com.example.uade.tpo.ecommerce.entities;

import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;

import com.example.uade.tpo.ecommerce.dto.body.ArtworkBody;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@DynamicUpdate
public class Artwork {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  @JsonBackReference
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private User artist;
  
  @Column
  private String description;

  @Column(columnDefinition = "IMAGE")
  @ToString.Exclude
  private byte[] image;

  @Column
  private double price;
  
  @Column
  private String title;
  
  @Column
  private int stock;
  
  @Column
  private boolean isHidden;

  @ManyToMany
  @JoinTable
  @JsonManagedReference
  private Set<Category> categories;
  
  @ManyToMany
  @JoinTable
  @JsonManagedReference
  private Set<Style> styles;
  
  @ManyToMany
  @JoinTable
  @JsonManagedReference
  private Set<Theme> themes;

  @ManyToOne
  @JoinColumn(name = "orientation_id", referencedColumnName = "id")
  private Orientation orientation;

  public Artwork() {
  }

  public Artwork(ArtworkBody body) {
    this.artist = body.getArtist();
    this.description = body.getDescription();
    this.image = body.getImage();
    this.price = body.getPrice();
    this.title = body.getTitle();
    this.stock = body.getStock();
    this.isHidden = body.isHidden();
    this.categories = body.getCategories();
    this.styles = body.getStyles();
    this.themes = body.getThemes();
    this.orientation = body.getOrientation();
  }
}
