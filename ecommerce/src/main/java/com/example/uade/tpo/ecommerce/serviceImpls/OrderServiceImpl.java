package com.example.uade.tpo.ecommerce.serviceImpls;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.ecommerce.dto.body.OrderBody;
import com.example.uade.tpo.ecommerce.entities.Artwork;
import com.example.uade.tpo.ecommerce.entities.Order;
import com.example.uade.tpo.ecommerce.entities.User;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.exceptions.InvalidOperationException;
import com.example.uade.tpo.ecommerce.repositories.OrderRepository;
import com.example.uade.tpo.ecommerce.services.ArtworkService;
import com.example.uade.tpo.ecommerce.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
  @Autowired
  OrderRepository orderRepository;

  @Autowired
  ArtworkService artworkService;

  public Set<Order> getUserOrders(User user) {
    List<Order> orderList = orderRepository.findAllByUserId(user.getId());
    return new HashSet<Order>(orderList);
  }

  public Optional<Order> getOrderById(Long id) {
    return orderRepository.findById(id);
  }

  public Order createOrder(OrderBody body)
      throws DuplicateException, InvalidOperationException {
    Artwork artwork = body.getArtwork();
    User user = body.getUser();

    if (user.getId() == artwork.getArtist().getId()) {
      throw new InvalidOperationException("El Artwork pertenece al User.");
    }

    List<Order> userOrders = orderRepository.findAllByUserId(user.getId());

    for (Order order : userOrders) {
      if (order.getArtwork().getId() == artwork.getId() && !order.getIsBought()) {
        throw new DuplicateException("Artwork ya incluido en el cart.");
      }
    }

    if (body.getQuantity() > artwork.getStock()) {
      throw new InvalidOperationException("Stock insuficiente. Stock: " + artwork.getStock() + " unidades.");
    }

    Order order = new Order(body);

    return orderRepository.save(order);
  }

  public Order updateOrder(Order order, OrderBody body)
      throws InvalidOperationException {
    if (order.getIsBought()) {
      throw new InvalidOperationException("No puedes editar un item ya comprado.");
    }

    Artwork artwork = body.getArtwork();

    if (body.getQuantity() > artwork.getStock()) {
      throw new InvalidOperationException("Stock insuficiente. Stock: " + artwork.getStock() + " unidades.");
    }

    order.setQuantity(body.getQuantity());

    return orderRepository.save(order);
  }

  public void deleteOrder(Order order) {
    orderRepository.delete(order);
  }

  public Order buyOrder(Order order) throws InvalidOperationException {
    if (order.getQuantity() > order.getArtwork().getStock()) {
      throw new InvalidOperationException("Stock insuficiente. Stock: " + order.getArtwork().getStock() + " unidades.");
    }

    artworkService.decrementStock(order.getArtwork(), order.getQuantity());
    order.setIsBought(true);

    return orderRepository.save(order);
  }

  public Set<Order> getCartOrders(User user) {
    List<Order> userOrders = orderRepository.findAllByUserIdIsNotBought(user.getId());
    return new HashSet<Order>(userOrders);
  }

  public Set<Order> getBoughtOrders(User user) {
    List<Order> userOrders = orderRepository.findAllByUserIdIsBought(user.getId());
    return new HashSet<Order>(userOrders);
  }
}
