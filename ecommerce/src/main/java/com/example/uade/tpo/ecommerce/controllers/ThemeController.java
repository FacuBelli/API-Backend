package com.example.uade.tpo.ecommerce.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.uade.tpo.ecommerce.dto.body.ThemeBody;
import com.example.uade.tpo.ecommerce.dto.request.ThemeRequest;
import com.example.uade.tpo.ecommerce.entities.Theme;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.exceptions.NotFoundException;
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
  public ResponseEntity<Theme> getThemeById(@PathVariable Long themeId) throws NotFoundException {
    Optional<Theme> theme = themeService.getThemeById(themeId);
    if (!theme.isPresent()) {
      throw new NotFoundException("El Theme(id): " + themeId + " no existe.");
    }

    return ResponseEntity.ok(theme.get());
  }

  @PostMapping
  public ResponseEntity<Theme> createTheme(@RequestBody ThemeRequest themeRequest)
      throws DuplicateException {
    ThemeBody body = ThemeBody.builder().name(themeRequest.getName()).build();
    Theme theme = themeService.createTheme(body);
    return ResponseEntity.created(URI.create("/theme/" + theme.getId())).body(theme);
  }
  @DeleteMapping("/{themeId}")
  public ResponseEntity<Void> deleteTheme(@PathVariable Long themeId) throws NotFoundException {
    themeService.deleteTheme(themeId);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{themeId}")
  public ResponseEntity<Theme> updateTheme(@PathVariable Long themeId, @RequestBody ThemeRequest themeRequest)
      throws NotFoundException {
    ThemeBody body = ThemeBody.builder().name(themeRequest.getName()).build();
    Theme updatedTheme = themeService.updateTheme(themeId, body);
    return ResponseEntity.ok(updatedTheme);
  }
}

