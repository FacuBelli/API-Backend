package com.example.uade.tpo.ecommerce.entities;

import java.sql.Blob;
import java.util.Set;

import com.example.uade.tpo.ecommerce.dto.body.ArtworkBody;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Artwork {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  private User artist;
  
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

  @ManyToMany
  @JoinTable
  private Set<Category> categories;
  
  @ManyToMany
  @JoinTable
  private Set<Style> styles;
  
  @ManyToMany
  @JoinTable
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
    this.hidden = body.isHidden();
    this.categories = body.getCategories();
    this.styles = body.getStyles();
    this.themes = body.getThemes();
    this.orientation = body.getOrientation();
  }
}
