package com.example.uade.tpo.ecommerce.serviceImpls;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.ecommerce.dto.body.UserBody;
import com.example.uade.tpo.ecommerce.entities.Artwork;
import com.example.uade.tpo.ecommerce.entities.CartItem;
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

    Set<Artwork> userFavorites = user.getFavoriteArtworks();
    for (Artwork favorite : userFavorites) {
      if (favorite.getId() == artwork.getId()) {
        throw new DuplicateException("Artwork ya incluido en favoritos.");
      }
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

  public User addCartItem(User user, Artwork artwork, Integer quantity)
      throws DuplicateException, InvalidOperationException {
    if (user.getId() == artwork.getArtist().getId()) {
      throw new InvalidOperationException("El Artwork pertenece al User.");
    }

    Set<CartItem> cart = user.getCart();
    for (CartItem cartItem : cart) {
      if (cartItem.getArtwork().getId() == artwork.getId()) {
        throw new DuplicateException("Artwork ya incluido en el cart.");
      }
    }

    if (quantity > artwork.getStock()) {
      throw new InvalidOperationException("Stock insuficiente. Stock: " + artwork.getStock() + " unidades.");
    }

    CartItem cartItem = new CartItem(user, artwork, quantity);
    cart.add(cartItem);

    return userRepository.save(user);
  }

  public User updateCartItem(User user, Artwork artwork, Integer quantity)
      throws NotFoundException, InvalidOperationException {
    Set<CartItem> cart = user.getCart();

    CartItem cartItem = null;
    for (CartItem item : cart) {
      if (item.getArtwork().getId() == artwork.getId()) {
        cartItem = item;
        break;
      }
    }

    if (cartItem == null) {
      throw new NotFoundException("Artwork no incluido en el carrito.");
    }

    if (quantity > artwork.getStock()) {
      throw new InvalidOperationException("Stock insuficiente. Stock: " + artwork.getStock() + " unidades.");
    }

    cartItem.setQuantity(quantity);

    return userRepository.save(user);
  }

  public User removeCartItem(User user, Artwork artwork) throws NotFoundException {
    Set<CartItem> cart = user.getCart();

    CartItem cartItem = null;
    for (CartItem item : cart) {
      if (item.getArtwork().getId() == artwork.getId()) {
        cartItem = item;
        break;
      }
    }

    if (cartItem == null) {
      throw new NotFoundException("Artwork no incluido en el carrito.");
    }

    cart.remove(cartItem);

    return userRepository.save(user);
  }

  public User clearCart(User user) {
    Set<CartItem> cart = user.getCart();

    cart.clear();

    return userRepository.save(user);
  }

  public User purchaseCart(User user) throws InvalidOperationException {
    Set<CartItem> cart = user.getCart();
    Set<CartItem> boughtArtworks = user.getBoughtArtworks();

    Iterator<CartItem> iterator = cart.iterator();
    while (iterator.hasNext()) {
      CartItem cartItem = iterator.next();
      Artwork artwork = cartItem.getArtwork();

      if (cartItem.getQuantity() > artwork.getStock()) {
        throw new InvalidOperationException("Stock insuficiente. Stock: " + artwork.getStock() + " unidades.");
      }

      boughtArtworks.add(cartItem);
      cart.remove(cartItem);
    }

    return userRepository.save(user);
  }
}
