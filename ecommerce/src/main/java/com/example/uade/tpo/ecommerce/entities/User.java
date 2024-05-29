package com.example.uade.tpo.ecommerce.entities;

import java.util.Set;

import com.example.uade.tpo.ecommerce.dto.body.UserBody;

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
    private String first_name;

    @Column
    private String last_name;

    @Column
    private boolean is_artist;

    @OneToMany(mappedBy = "artist")
    private Set<Artwork> artworks;

    @ManyToMany
    @JoinTable(inverseJoinColumns = { @JoinColumn(name = "bought_artwork_id") })
    private Set<Artwork> bought_artworks;

    @ManyToMany
    @JoinTable(inverseJoinColumns = { @JoinColumn(name = "favorite_artwork_id") })
    private Set<Artwork> favorite_artworks;

    @OneToMany
    private Set<CartItem> cart;

    public User() {
  }

  public User(UserBody body) {
    this.biography = body.getBiography();
    this.email = body.getEmail();
    this.password = body.getPassword();
    this.first_name = body.getFirstName();
    this.last_name = body.getLastName();
    this.is_artist = body.isArtist();

  }
}
