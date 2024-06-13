package com.example.uade.tpo.ecommerce.dto.request;

import java.util.Set;

import lombok.Data;

@Data
public class ArtworkRequest {
  private Long artistId;

  private String description;

  private byte[] image;

  private double price;

  private String title;

  private int stock;

  private boolean isHidden;

  private Set<String> categories;

  private Set<String> styles;

  private Set<String> themes;

  private String orientation;

  private double discount;

  public ArtworkRequest() {
  }

  public ArtworkRequest(Long artistId, String description, byte[] image, double price, String title, int stock,
      boolean isHidden, Set<String> categories, Set<String> styles, Set<String> themes, String orientation,
      double discount) {
    this.artistId = artistId;
    this.description = description;
    this.image = image;
    this.price = price;
    this.title = title;
    this.stock = stock;
    this.isHidden = isHidden;
    this.categories = categories;
    this.styles = styles;
    this.themes = themes;
    this.orientation = orientation;
    this.discount = discount;
  }

}
