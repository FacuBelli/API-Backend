package com.example.uade.tpo.ecommerce.entities;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.uade.tpo.ecommerce.dto.body.UserBody;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String biography;

  @Column
  private String email;

  @JsonIgnore
  @Column
  private String password;

  @Column
  private String firstName;

  @Column
  private String lastName;

  @Column
  private Boolean isArtist;

  @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
  @JsonManagedReference
  private Set<Artwork> created;

  @ManyToMany
  @JoinTable(inverseJoinColumns = { @JoinColumn(name = "favorite_artwork_id") })
  @JsonManagedReference
  private Set<Artwork> favorites;

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

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(isArtist ? "Artist" : "User"));
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
