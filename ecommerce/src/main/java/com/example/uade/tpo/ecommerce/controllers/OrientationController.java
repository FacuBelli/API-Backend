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

import com.example.uade.tpo.ecommerce.entities.Orientation;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.services.OrientationService;

@RestController
@RequestMapping("orientation")
public class OrientationController {
  @Autowired
  private OrientationService orientationService;

  @GetMapping
  public ResponseEntity<List<Orientation>> getOrientations() {
    return ResponseEntity.ok(orientationService.getOrientations());
  }

  @GetMapping("/{orientationId}")
  public ResponseEntity<Orientation> getCategoryById(@PathVariable Long orientationId) {
    Optional<Orientation> result = orientationService.getOrientationById(orientationId);
    if (result.isPresent())
      return ResponseEntity.ok(result.get());
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<Object> createOrientation(@RequestBody Orientation orientationRequest)
      throws DuplicateException {
    Orientation result = orientationService.createOrientation(orientationRequest.getName());
    return ResponseEntity.created(URI.create("/orientation/" + result.getId())).body(result);
  }
}
