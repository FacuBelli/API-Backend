package com.example.uade.tpo.ecommerce.dto.request;

import lombok.Data;

@Data
public class OrientationRequest {
  private String name;

  public OrientationRequest() {
  }

  public OrientationRequest(String name) {
    this.name = name;
  }
}
