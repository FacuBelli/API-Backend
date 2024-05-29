package com.example.uade.tpo.ecommerce.services;

import java.util.List;
import java.util.Optional;

import com.example.uade.tpo.ecommerce.dto.body.UserBody;
import com.example.uade.tpo.ecommerce.entities.User;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;

public interface UserService {
  public List<User> getUsers();

  public Optional<User> getUserById(Long id);

  public User createUser(UserBody body) throws DuplicateException;
}
