package com.example.uade.tpo.ecommerce.dto.request;

import lombok.Data;

@Data
public class OrientationRequest {
  private Long id;

  private String name;

  public OrientationRequest() {
  }

  public OrientationRequest(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
