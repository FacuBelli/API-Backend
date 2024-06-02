package com.example.uade.tpo.ecommerce.dto.request;

import lombok.Data;

@Data
public class ThemeRequest {
  private String name;

  public ThemeRequest() {
  }

  public ThemeRequest(String name) {
    this.name = name;
  }
}
