package com.example.uade.tpo.ecommerce.dto.request;

import lombok.Data;

@Data
public class UserRequest {
  private Long id;

  private String biography;

  private String email;

  private String password;

  private String firstName;

  private String lastName;

  private boolean isArtist;

  public UserRequest() {
  }

  public UserRequest(Long id, String biography, String email, String password, String firstName, String lastName,
      boolean isArtist) {
    this.id = id;
    this.biography = biography;
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.isArtist = isArtist;
  }
}
