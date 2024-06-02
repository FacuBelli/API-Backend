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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.uade.tpo.ecommerce.dto.body.UserBody;
import com.example.uade.tpo.ecommerce.dto.request.UserRequest;
import com.example.uade.tpo.ecommerce.entities.Artwork;
import com.example.uade.tpo.ecommerce.entities.User;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.exceptions.InvalidOperationException;
import com.example.uade.tpo.ecommerce.exceptions.NotFoundException;
import com.example.uade.tpo.ecommerce.services.ArtworkService;
import com.example.uade.tpo.ecommerce.services.UserService;

@RestController
@RequestMapping("user")

public class UserController {
  @Autowired
  private UserService userService;

  @Autowired
  private ArtworkService artworkService;

  @GetMapping
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.ok(userService.getUsers());
  }

  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable Long userId) {
    Optional<User> result = userService.getUserById(userId);
    if (result.isPresent())
      return ResponseEntity.ok(result.get());
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<Object> createUser(@RequestBody UserRequest userRequest)
      throws DuplicateException {
    UserBody body = UserBody.builder()
        .biography(userRequest.getBiography())
        .email(userRequest.getEmail())
        .firstName(userRequest.getFirstName())
        .isArtist(userRequest.isArtist())
        .lastName(userRequest.getLastName())
        .password(userRequest.getPassword())
        .build();
    User result = userService.createUser(body);
    return ResponseEntity.created(URI.create("/user/" + result.getId())).body(result);
  }

  @PostMapping("/{userId}/favorite/{artworkId}")
  public ResponseEntity<User> addFavorite(@PathVariable Long userId, @PathVariable Long artworkId)
      throws DuplicateException, NotFoundException, InvalidOperationException {
    Optional<User> user = userService.getUserById(userId);
    if (!user.isPresent()) {
      throw new NotFoundException("El Usuario no existe:\n { userId: " + userId + " }");
    }

    Optional<Artwork> artwork = artworkService.getArtworkById(artworkId);
    if (!artwork.isPresent()) {
      throw new NotFoundException("El Artwork no existe:\n { artworkId: " + artworkId + " }" );
    }

    User result = userService.addFavorite(user.get(), artwork.get());
    return ResponseEntity.ok(result);
  }

  @DeleteMapping("/{userId}/favorite/{artworkId}")
  public ResponseEntity<User> removeFavorite(@PathVariable Long userId, @PathVariable Long artworkId)
      throws NotFoundException {
    Optional<User> user = userService.getUserById(userId);
    if (!user.isPresent()) {
      throw new NotFoundException("El Usuario no existe:\n { userId: " + userId + " }");
    }

    Optional<Artwork> artwork = artworkService.getArtworkById(artworkId);
    if (!artwork.isPresent()) {
      throw new NotFoundException("El Artwork no existe:\n { artworkId: " + artworkId + " }" );
    }

    User result = userService.removeFavorite(user.get(), artwork.get());
    return ResponseEntity.ok(result);
  }

  @DeleteMapping("/{userId}/favorite")
  public ResponseEntity<User> clearFavorites(@PathVariable Long userId)
      throws NotFoundException {
    Optional<User> user = userService.getUserById(userId);
    if (!user.isPresent()) {
      throw new NotFoundException("El Usuario no existe:\n { userId: " + userId + " }");
    }

    User result = userService.clearFavorites(user.get());
    return ResponseEntity.ok(result);
  }
}
