package com.example.uade.tpo.ecommerce.entities;

import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;

import com.example.uade.tpo.ecommerce.dto.body.ArtworkBody;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
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
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private User artist;

  @Column
  private String description;

  @Lob
  @JsonIgnore
  @Column
  @Transient
  @ToString.Exclude
  private byte[] image;

  @Column
  private Double price;

  @Column
  private String title;

  @Column
  private Integer stock;

  @Column
  private Boolean isHidden;

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

  @Column
  private Double discount;

  public Artwork() {
  }

  public Artwork(ArtworkBody body) {
    this.artist = body.getArtist();
    this.description = body.getDescription();
    this.image = body.getImage();
    this.price = body.getPrice();
    this.title = body.getTitle();
    this.stock = body.getStock();
    this.isHidden = body.getIsHidden();
    this.categories = body.getCategories();
    this.styles = body.getStyles();
    this.themes = body.getThemes();
    this.orientation = body.getOrientation();
    this.discount = body.getDiscount();
  }
}
