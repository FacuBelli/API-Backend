package com.example.uade.tpo.ecommerce.entities;

import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;

import com.example.uade.tpo.ecommerce.dto.body.UserBody;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@DynamicUpdate
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String biography;

  @Column
  private String email;

  @Column
  private String password;

  @Column
  private String firstName;

  @Column
  private String lastName;

  @Column
  private boolean isArtist;

  @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
  @JsonManagedReference
  private Set<Artwork> artworks;

  @ManyToMany
  @JoinTable(inverseJoinColumns = { @JoinColumn(name = "bought_artwork_id") })
  @JsonManagedReference
  private Set<Artwork> boughtArtworks;

  @ManyToMany
  @JoinTable(inverseJoinColumns = { @JoinColumn(name = "favorite_artwork_id") })
  @JsonManagedReference
  private Set<Artwork> favoriteArtworks;

  @OneToMany
  private Set<CartItem> cart;

  public User() {
  }

  public User(UserBody body) {
    this.biography = body.getBiography();
    this.email = body.getEmail();
    this.password = body.getPassword();
    this.firstName = body.getFirstName();
    this.lastName = body.getLastName();
    this.isArtist = body.isArtist();
  }
}
