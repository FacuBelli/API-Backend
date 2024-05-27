package com.example.uade.tpo.ecommerce.services;

import java.util.List;
import java.util.Optional;

import com.example.uade.tpo.ecommerce.dto.ArtworkBody;
import com.example.uade.tpo.ecommerce.entities.Artwork;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;

public interface ArtworkService {
  public List<Artwork> getArtworks();

  public List<Artwork> getArtworksByUserId(Long id);

  public Optional<Artwork> getArtworkById(Long id);

  public Artwork createArtwork(ArtworkBody body) throws DuplicateException;
}
