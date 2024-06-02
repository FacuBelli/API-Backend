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

import com.example.uade.tpo.ecommerce.dto.body.OrientationBody;
import com.example.uade.tpo.ecommerce.dto.request.OrientationRequest;
import com.example.uade.tpo.ecommerce.entities.Orientation;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.exceptions.NotFoundException;
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
  public ResponseEntity<Orientation> getOrientationById(@PathVariable Long orientationId) throws NotFoundException {
    Optional<Orientation> orientation = orientationService.getOrientationById(orientationId);
    if (!orientation.isPresent()) {
      throw new NotFoundException("La Orientation(id): " + orientationId + " no existe.");
    }

    return ResponseEntity.ok(orientation.get());
  }

  @PostMapping
  public ResponseEntity<Object> createOrientation(@RequestBody OrientationRequest orientationRequest)
      throws DuplicateException {
    OrientationBody body = OrientationBody.builder().name(orientationRequest.getName()).build();
    Orientation orientation = orientationService.createOrientation(body);
    return ResponseEntity.created(URI.create("/orientation/" + orientation.getId())).body(orientation);
  }
}
