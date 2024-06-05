package com.example.uade.tpo.ecommerce.dto.request;

import lombok.Data;

@Data
public class OrderRequest {
  private Long userId;

  private Long artworkId;

  private Integer quantity;

  public OrderRequest() {
  }

  public OrderRequest(Long userId, Long artworkId, Integer quantity) {
    this.userId = userId;
    this.artworkId = artworkId;
    this.quantity = quantity;
  }
}
