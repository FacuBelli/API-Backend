package com.example.uade.tpo.ecommerce.dto.request;

import lombok.Data;

@Data
public class StyleRequest {
  private String name;

  public StyleRequest() {
  }

  public StyleRequest(String name) {
    this.name = name;
  }
}
