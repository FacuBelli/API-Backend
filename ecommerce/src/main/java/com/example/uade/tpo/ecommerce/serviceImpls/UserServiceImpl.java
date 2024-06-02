package com.example.uade.tpo.ecommerce.serviceImpls;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.ecommerce.dto.body.UserBody;
import com.example.uade.tpo.ecommerce.entities.Artwork;
import com.example.uade.tpo.ecommerce.entities.User;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.exceptions.InvalidOperationException;
import com.example.uade.tpo.ecommerce.exceptions.NotFoundException;
import com.example.uade.tpo.ecommerce.repositories.UserRepository;
import com.example.uade.tpo.ecommerce.services.UserService;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }

  public User createUser(UserBody body) throws DuplicateException {
    Optional<User> emailUser = userRepository.findByEmail(body.getEmail());
    if (emailUser.isPresent())
      throw new DuplicateException("Un usuario con el email '" + body.getEmail() + "' ya existe.");
    return userRepository.save(new User(body));
  }

  public User addFavorite(User user, Artwork artwork) throws DuplicateException, InvalidOperationException {
    if (user.getId() == artwork.getArtist().getId()) {
      throw new InvalidOperationException("El Artwork pertenece al User.");
    }
    
    Set<Artwork> userFavorites = user.getFavoriteArtworks();
    for (Artwork favorite : userFavorites) {
      if (favorite.getId() == artwork.getId()) throw new DuplicateException("Artwork ya incluido en favoritos.");
    }

    userFavorites.add(artwork);

    return userRepository.save(user);
  }

  public User removeFavorite(User user, Artwork artwork) throws NotFoundException {
    Set<Artwork> userFavorites = user.getFavoriteArtworks();
    
    boolean contained = userFavorites.remove(artwork);

    if (!contained) {
      throw new NotFoundException("Artwork no incluido en favoritos.");
    }
    
    return userRepository.save(user);
  }

  public User clearFavorites(User user) {
    Set<Artwork> userFavorites = user.getFavoriteArtworks();

    userFavorites.clear();

    return userRepository.save(user);
  }

  public User addCartItem(User user, Artwork artwork) {
    return null;
  }

  public User removeCartItem(User user, Artwork artwork) throws NotFoundException {
    return null;
  }

  public User clearCart(User user) {
    return null;
  }
}
