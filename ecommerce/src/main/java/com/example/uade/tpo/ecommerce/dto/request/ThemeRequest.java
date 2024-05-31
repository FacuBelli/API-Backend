package com.example.uade.tpo.ecommerce.dto.request;

import lombok.Data;

@Data
public class ThemeRequest {
  private Long id;

  private String name;

  public ThemeRequest() {
  }

  public ThemeRequest(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
