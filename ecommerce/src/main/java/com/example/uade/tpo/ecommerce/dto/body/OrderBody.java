package com.example.uade.tpo.ecommerce.dto.body;

import com.example.uade.tpo.ecommerce.entities.Artwork;
import com.example.uade.tpo.ecommerce.entities.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderBody {
  private User user;

  private Artwork artwork;

  private Integer quantity;
}
