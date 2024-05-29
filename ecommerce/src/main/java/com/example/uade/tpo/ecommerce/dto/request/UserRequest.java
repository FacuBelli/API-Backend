package com.example.uade.tpo.ecommerce.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
  private String biography;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private boolean isArtist;
}
