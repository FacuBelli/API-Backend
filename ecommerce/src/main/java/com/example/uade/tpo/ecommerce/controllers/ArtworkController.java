package com.example.uade.tpo.ecommerce.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.uade.tpo.ecommerce.dto.ArtworkBody;
import com.example.uade.tpo.ecommerce.entities.Artwork;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.services.ArtworkService;

@RestController
@RequestMapping("artwork")
public class ArtworkController {
  @Autowired
  private ArtworkService artworkService;

  @GetMapping
  public ResponseEntity<List<Artwork>> getCategories() {
    return ResponseEntity.ok(artworkService.getArtworks());
  }

  @GetMapping("/{artworkId}")
  public ResponseEntity<Artwork> getArtworkById(@PathVariable Long artworkId) {
    Optional<Artwork> result = artworkService.getArtworkById(artworkId);
    if (result.isPresent())
      return ResponseEntity.ok(result.get());
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<Object> createCategory(@RequestBody ArtworkBody artworkRequest)
      throws DuplicateException {
        Artwork result = artworkService.createArtwork(artworkRequest);
    return ResponseEntity.created(URI.create("/artwork/" + result.getId())).body(result);
  }
}
