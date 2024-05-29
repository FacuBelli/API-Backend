package com.example.uade.tpo.ecommerce.dto.request;

import java.sql.Blob;
import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArtworkRequest {
  private Long artistId;

  private String description;

  private Blob image;

  private double price;

  private String title;

  private int stock;

  private boolean hidden;

  private Set<String> categories;

  private Set<String> styles;

  private Set<String> themes;

  private String orientation;
}
