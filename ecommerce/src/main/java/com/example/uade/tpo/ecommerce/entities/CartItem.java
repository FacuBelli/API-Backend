package com.example.uade.tpo.ecommerce.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class CartItem {
  @Id
  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  private User user;

  @Id
  @OneToOne
  @JoinColumn(name = "artwork_id", referencedColumnName = "id", nullable = false)
  private Artwork artwork;

  @Column
  private int quantity;
}
