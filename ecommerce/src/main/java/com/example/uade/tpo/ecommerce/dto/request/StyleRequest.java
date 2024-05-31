package com.example.uade.tpo.ecommerce.dto.request;

import lombok.Data;

@Data
public class StyleRequest {
  private Long id;

  private String name;

  public StyleRequest() {
  }

  public StyleRequest(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
