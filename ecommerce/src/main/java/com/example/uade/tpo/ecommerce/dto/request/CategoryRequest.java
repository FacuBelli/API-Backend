package com.example.uade.tpo.ecommerce.dto.request;

import lombok.Data;

@Data
public class CategoryRequest {
  private Long id;

  private String name;

  public CategoryRequest() {
  }

  public CategoryRequest(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
