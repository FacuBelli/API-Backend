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

import com.example.uade.tpo.ecommerce.dto.body.UserBody;
import com.example.uade.tpo.ecommerce.dto.request.UserRequest;
import com.example.uade.tpo.ecommerce.entities.User;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.services.UserService;

@RestController
@RequestMapping("user")

public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> getUser() {
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
}
