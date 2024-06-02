package com.example.uade.tpo.ecommerce.dto.request;

import lombok.Data;

@Data
public class CategoryRequest {
  private String name;

  public CategoryRequest() {
  }

  public CategoryRequest(String name) {
    this.name = name;
  }
}
