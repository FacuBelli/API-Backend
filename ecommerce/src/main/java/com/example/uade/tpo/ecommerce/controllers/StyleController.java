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

import com.example.uade.tpo.ecommerce.dto.StyleBody;
import com.example.uade.tpo.ecommerce.entities.Style;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.services.StyleService;

@RestController
@RequestMapping("style")
public class StyleController {
  @Autowired
  private StyleService styleService;

  @GetMapping
  public ResponseEntity<List<Style>> getStyles() {
    return ResponseEntity.ok(styleService.getStyles());
  }

  @GetMapping("/{styleId}")
  public ResponseEntity<Style> getStylesById(@PathVariable Long styleId) {
    Optional<Style> result = styleService.getStyleById(styleId);
    if (result.isPresent())
      return ResponseEntity.ok(result.get());
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<Object> createStyle(@RequestBody StyleBody styleRequest) throws DuplicateException {
    Style result = styleService.createStyle(styleRequest);
    return ResponseEntity.created(URI.create("/style/" + result.getId())).body(result);
  }
}
