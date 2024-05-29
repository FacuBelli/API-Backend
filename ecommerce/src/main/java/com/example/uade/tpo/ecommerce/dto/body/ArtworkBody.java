package com.example.uade.tpo.ecommerce.dto.body;

import java.sql.Blob;
import java.util.Set;

import com.example.uade.tpo.ecommerce.entities.Category;
import com.example.uade.tpo.ecommerce.entities.Orientation;
import com.example.uade.tpo.ecommerce.entities.Style;
import com.example.uade.tpo.ecommerce.entities.Theme;
import com.example.uade.tpo.ecommerce.entities.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArtworkBody {
  private User artist;

  private String description;

  private Blob image;

  private double price;

  private String title;

  private int stock;

  private boolean hidden;

  private Set<Category> categories;

  private Set<Style> styles;

  private Set<Theme> themes;

  private Orientation orientation;
}