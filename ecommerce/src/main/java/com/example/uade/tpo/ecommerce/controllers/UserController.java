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

import com.example.uade.tpo.ecommerce.dto.body.UserBody;
import com.example.uade.tpo.ecommerce.dto.request.UserRequest;
import com.example.uade.tpo.ecommerce.entities.Artwork;
import com.example.uade.tpo.ecommerce.entities.User;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.exceptions.InvalidOperationException;
import com.example.uade.tpo.ecommerce.exceptions.InvalidQuantityException;
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
  public ResponseEntity<User> getUserById(@PathVariable Long userId) throws NotFoundException {
    Optional<User> user = userService.getUserById(userId);
    if (!user.isPresent()) {
      throw new NotFoundException("El User(id): " + userId + " no existe.");
    }

    return ResponseEntity.ok(user.get());
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest)
      throws DuplicateException {
    UserBody body = UserBody.builder()
        .biography(userRequest.getBiography())
        .email(userRequest.getEmail())
        .firstName(userRequest.getFirstName())
        .isArtist(userRequest.isArtist())
        .lastName(userRequest.getLastName())
        .password(userRequest.getPassword())
        .build();
    User user = userService.createUser(body);
    return ResponseEntity.created(URI.create("/user/" + user.getId())).body(user);
  }

  @PutMapping("/{userId}")
  public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody UserRequest userRequest) 
      throws NotFoundException {
    Optional<User> user = userService.getUserById(userId);
    if (!user.isPresent()) {
      throw new NotFoundException("El User(id): " + userId + " no existe.");
    }

    UserBody body = UserBody.builder()
        .biography(userRequest.getBiography())
        .email(userRequest.getEmail())
        .firstName(userRequest.getFirstName())
        .isArtist(userRequest.isArtist())
        .lastName(userRequest.getLastName())
        .password(userRequest.getPassword())
        .build();
    User updatedUser = userService.updateUser(user.get(), body);
    return ResponseEntity.ok(updatedUser);
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long userId) throws NotFoundException {
    Optional<User> user = userService.getUserById(userId);
    if (!user.isPresent()) {
      throw new NotFoundException("El User(id): " + userId + " no existe.");
    }

    userService.deleteUser(user.get());
    return ResponseEntity.noContent().build();
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
      throw new NotFoundException("El Artwork no existe:\n { artworkId: " + artworkId + " }");
    }

    User newUser = userService.addFavorite(user.get(), artwork.get());
    return ResponseEntity.ok(newUser);
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
      throw new NotFoundException("El Artwork no existe:\n { artworkId: " + artworkId + " }");
    }

    User newUser = userService.removeFavorite(user.get(), artwork.get());
    return ResponseEntity.ok(newUser);
  }

  @DeleteMapping("/{userId}/favorite")
  public ResponseEntity<User> clearFavorites(@PathVariable Long userId)
      throws NotFoundException {
    Optional<User> user = userService.getUserById(userId);
    if (!user.isPresent()) {
      throw new NotFoundException("El Usuario no existe:\n { userId: " + userId + " }");
    }

    User newUser = userService.clearFavorites(user.get());
    return ResponseEntity.ok(newUser);
  }

  @PostMapping("/{userId}/cart/{artworkId}/{quantity}")
  public ResponseEntity<User> addCartItem(@PathVariable Long userId, @PathVariable Long artworkId,
      @PathVariable int quantity)
      throws NotFoundException, InvalidQuantityException, DuplicateException, InvalidOperationException {
    Optional<User> user = userService.getUserById(userId);
    if (!user.isPresent()) {
      throw new NotFoundException("El Usuario no existe:\n { userId: " + userId + " }");
    }

    Optional<Artwork> artwork = artworkService.getArtworkById(artworkId);
    if (!artwork.isPresent()) {
      throw new NotFoundException("El Artwork no existe:\n { artworkId: " + artworkId + " }");
    }

    if (quantity <= 0) {
      throw new InvalidQuantityException("Cantidad solicitada invalida.");
    }

    User newUser = userService.addCartItem(user.get(), artwork.get(), quantity);

    return ResponseEntity.ok(newUser);
  }

  @PutMapping("/{userId}/cart/{artworkId}/{quantity}")
  public ResponseEntity<User> updateCartItem(@PathVariable Long userId, @PathVariable Long artworkId,
      @PathVariable int quantity)
      throws NotFoundException, InvalidQuantityException, DuplicateException, InvalidOperationException {
    Optional<User> user = userService.getUserById(userId);
    if (!user.isPresent()) {
      throw new NotFoundException("El Usuario no existe:\n { userId: " + userId + " }");
    }

    Optional<Artwork> artwork = artworkService.getArtworkById(artworkId);
    if (!artwork.isPresent()) {
      throw new NotFoundException("El Artwork no existe:\n { artworkId: " + artworkId + " }");
    }

    if (quantity <= 0) {
      throw new InvalidQuantityException("Cantidad solicitada invalida.");
    }

    User newUser = userService.updateCartItem(user.get(), artwork.get(), quantity);

    return ResponseEntity.ok(newUser);
  }

  @DeleteMapping("/{userId}/cart/{artworkId}")
  public ResponseEntity<User> removeCartItem(@PathVariable Long userId, @PathVariable Long artworkId)
      throws NotFoundException {
    Optional<User> user = userService.getUserById(userId);
    if (!user.isPresent()) {
      throw new NotFoundException("El Usuario no existe:\n { userId: " + userId + " }");
    }

    Optional<Artwork> artwork = artworkService.getArtworkById(artworkId);
    if (!artwork.isPresent()) {
      throw new NotFoundException("El Artwork no existe:\n { artworkId: " + artworkId + " }");
    }

    User newUser = userService.removeCartItem(user.get(), artwork.get());

    return ResponseEntity.ok(newUser);
  }

  @PostMapping("/{userId}/cart/purchase")
  public ResponseEntity<User> purchaseCart(@PathVariable Long userId)
      throws NotFoundException, InvalidOperationException {
    Optional<User> user = userService.getUserById(userId);
    if (!user.isPresent()) {
      throw new NotFoundException("El Usuario no existe:\n { userId: " + userId + " }");
    }

    User newUser = userService.purchaseCart(user.get());

    return ResponseEntity.ok(newUser);
  }
}
