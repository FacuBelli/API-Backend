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

import com.example.uade.tpo.ecommerce.dto.body.ThemeBody;
import com.example.uade.tpo.ecommerce.dto.request.ThemeRequest;
import com.example.uade.tpo.ecommerce.entities.Theme;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.services.ThemeService;

@RestController
@RequestMapping("theme")

public class ThemeController {
  @Autowired
  private ThemeService themeService;

  @GetMapping
  public ResponseEntity<List<Theme>> getThemes() {
    return ResponseEntity.ok(themeService.getThemes());
  }

  @GetMapping("/{themeId}")
  public ResponseEntity<Theme> getArtworkById(@PathVariable Long artworkId) {
    Optional<Theme> result = themeService.getThemeById(artworkId);
    if (result.isPresent())
      return ResponseEntity.ok(result.get());
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<Object> createTheme(@RequestBody ThemeRequest themeRequest)
      throws DuplicateException {
    ThemeBody body = ThemeBody.builder().name(themeRequest.getName()).build();
    Theme result = themeService.createTheme(body);
    return ResponseEntity.created(URI.create("/theme/" + result.getId())).body(result);
  }
}
