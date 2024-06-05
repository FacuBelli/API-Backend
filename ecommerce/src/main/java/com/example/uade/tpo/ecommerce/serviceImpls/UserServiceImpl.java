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

  public User updateUser(User user, UserBody body) {
    if (body.getBiography() != null && !body.getBiography().equals(user.getBiography())) {
      user.setBiography(body.getBiography());
    }

    if (body.getEmail() != null && !body.getEmail().equals(user.getEmail())) {
      user.setEmail(body.getEmail());
    }

    if (body.getFirstName() != null && !body.getFirstName().equals(user.getFirstName())) {
      user.setFirstName(body.getFirstName());
    }

    if (body.getLastName() != null && !body.getLastName().equals(user.getLastName())) {
      user.setLastName(body.getLastName());
    }

    if (body.getPassword() != null && !body.getPassword().equals(user.getPassword())) {
      user.setPassword(body.getPassword());
    }

    return userRepository.save(user);
  }

  public void deleteUser(User user) {
    userRepository.delete(user);
  }

  public User addFavorite(User user, Artwork artwork) throws DuplicateException, InvalidOperationException {
    if (user.getId() == artwork.getArtist().getId()) {
      throw new InvalidOperationException("El Artwork pertenece al User.");
    }

    Set<Artwork> userFavorites = user.getFavorites();
    for (Artwork favorite : userFavorites) {
      if (favorite.getId() == artwork.getId()) {
        throw new DuplicateException("Artwork ya incluido en favoritos.");
      }
    }

    userFavorites.add(artwork);

    return userRepository.save(user);
  }

  public User removeFavorite(User user, Artwork artwork) throws NotFoundException {
    Set<Artwork> userFavorites = user.getFavorites();

    boolean contained = userFavorites.remove(artwork);

    if (!contained) {
      throw new NotFoundException("Artwork no incluido en favoritos.");
    }

    return userRepository.save(user);
  }

  public User clearFavorites(User user) {
    Set<Artwork> userFavorites = user.getFavorites();

    userFavorites.clear();

    return userRepository.save(user);
  }
}
