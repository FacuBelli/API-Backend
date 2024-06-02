package com.example.uade.tpo.ecommerce.services;

import java.util.List;
import java.util.Optional;

import com.example.uade.tpo.ecommerce.dto.body.UserBody;
import com.example.uade.tpo.ecommerce.entities.Artwork;
import com.example.uade.tpo.ecommerce.entities.User;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.exceptions.InvalidOperationException;
import com.example.uade.tpo.ecommerce.exceptions.NotFoundException;

public interface UserService {
  public List<User> getUsers();

  public Optional<User> getUserById(Long id);

  public User createUser(UserBody body) throws DuplicateException;

  public User updateUser(User user, UserBody body);

  public void deleteUser(User user);

  public User addFavorite(User user, Artwork artwork) throws DuplicateException, InvalidOperationException;

  public User removeFavorite(User user, Artwork artwork) throws NotFoundException;

  public User clearFavorites(User user);

  public User addCartItem(User user, Artwork artwork);

  public User removeCartItem(User user, Artwork artwork) throws NotFoundException;

  public User clearCart(User user);
}
