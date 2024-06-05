package com.example.uade.tpo.ecommerce.entities;

import org.hibernate.annotations.DynamicUpdate;

import com.example.uade.tpo.ecommerce.dto.body.OrderBody;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@DynamicUpdate
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "artwork_id", referencedColumnName = "id", nullable = false)
  private Artwork artwork;

  @Column
  private Integer quantity;

  @Column
  private Boolean isBought;

  public Order() {
  }

  public Order(OrderBody body) {
    this.user = body.getUser();
    this.artwork = body.getArtwork();
    this.quantity = body.getQuantity();
    this.isBought = false;
  }
}
