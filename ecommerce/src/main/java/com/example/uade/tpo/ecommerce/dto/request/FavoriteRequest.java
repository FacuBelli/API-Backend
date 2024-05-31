package com.example.uade.tpo.ecommerce.dto.request;

import lombok.Data;

@Data
public class FavoriteRequest {
  private UserRequest user;
  
  private ArtworkRequest artwork;

  public FavoriteRequest() {
  }

  public FavoriteRequest(UserRequest user, ArtworkRequest artwork) {
    this.user = user;
    this.artwork = artwork;
  }
}
