package com.example.uade.tpo.ecommerce.controllers.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String biography;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private boolean isArtist;
}
