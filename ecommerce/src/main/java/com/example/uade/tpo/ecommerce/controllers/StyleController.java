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

import com.example.uade.tpo.ecommerce.dto.body.StyleBody;
import com.example.uade.tpo.ecommerce.dto.request.StyleRequest;
import com.example.uade.tpo.ecommerce.entities.Style;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.exceptions.NotFoundException;
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
  public ResponseEntity<Style> getStyleById(@PathVariable Long styleId) throws NotFoundException {
    Optional<Style> style = styleService.getStyleById(styleId);
    if (!style.isPresent()) {
      throw new NotFoundException("El Style(id): " + styleId + " no existe.");
    }

    return ResponseEntity.ok(style.get());
  }

  @PostMapping
  public ResponseEntity<Style> createStyle(@RequestBody StyleRequest styleRequest) throws DuplicateException {
    StyleBody body = StyleBody.builder().name(styleRequest.getName()).build();
    Style style = styleService.createStyle(body);
    return ResponseEntity.created(URI.create("/style/" + style.getId())).body(style);
  }

  @DeleteMapping("/{styleId}")
  public ResponseEntity<Void> deleteStyle(@PathVariable Long styleId) throws NotFoundException {
    Optional<Style> style = styleService.getStyleById(styleId);
    if (!style.isPresent()) {
      throw new NotFoundException("El Style(id): " + styleId + " no existe.");
    }
    styleService.deleteStyle(style.get());
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{styleId}")
  public ResponseEntity<Style> updateStyle(@PathVariable Long styleId, @RequestBody StyleRequest styleRequest)
      throws NotFoundException {
    Optional<Style> style = styleService.getStyleById(styleId);
    if (!style.isPresent()) {
      throw new NotFoundException("El Style(id): " + styleId + " no existe.");
    }
    StyleBody body = StyleBody.builder().name(styleRequest.getName()).build();
    Style updatedStyle = styleService.updateStyle(style.get(), body);
    return ResponseEntity.ok(updatedStyle);
  }
}
