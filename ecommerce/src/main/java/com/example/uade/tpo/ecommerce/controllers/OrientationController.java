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
  public ResponseEntity<Orientation> createOrientation(@RequestBody OrientationRequest orientationRequest)
      throws DuplicateException {
    OrientationBody body = OrientationBody.builder().name(orientationRequest.getName()).build();
    Orientation orientation = orientationService.createOrientation(body);
    return ResponseEntity.created(URI.create("/orientation/" + orientation.getId())).body(orientation);
  }

  @PutMapping("/{orientationId}")
  public ResponseEntity<Orientation> updateOrientation(@PathVariable Long orientationId,
      @RequestBody OrientationRequest orientationRequest) throws NotFoundException {
    Optional<Orientation> orientation = orientationService.getOrientationById(orientationId);
    if (!orientation.isPresent()) {
      throw new NotFoundException("La Orientation(id): " + orientationId + " no existe.");
    }

    OrientationBody body = OrientationBody.builder().name(orientationRequest.getName()).build();
    Orientation newOrientation = orientationService.updateOrientation(orientation.get(), body);

    return ResponseEntity.ok(newOrientation);
  }

  @DeleteMapping("/{orientationId}")
  public ResponseEntity<Void> deleteOrientation(@PathVariable Long orientationId) throws NotFoundException {
    Optional<Orientation> orientation = orientationService.getOrientationById(orientationId);
    if (!orientation.isPresent()) {
      throw new NotFoundException("La Orientation(id): " + orientationId + " no existe.");
    }

    orientationService.deleteOrientation(orientation.get());

    return ResponseEntity.ok().build();
  }
}
