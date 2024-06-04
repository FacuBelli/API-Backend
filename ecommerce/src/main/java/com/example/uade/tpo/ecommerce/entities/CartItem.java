package com.example.uade.tpo.ecommerce.entities;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@DynamicUpdate
public class CartItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  @JsonBackReference
  @ToString.Exclude
  private User user;

  @ManyToOne
  @JoinColumn(name = "artwork_id", referencedColumnName = "id", nullable = false)
  @JsonBackReference
  @ToString.Exclude
  private Artwork artwork;

  @Column
  private Integer quantity;

  public CartItem() {
  }

  public CartItem(User user, Artwork artwork, Integer quantity) {
    this.user = user;
    this.artwork = artwork;
    this.quantity = quantity;
  }
}
