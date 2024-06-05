package com.example.uade.tpo.ecommerce.controllers;

import java.util.Optional;
import java.util.Set;

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

import com.example.uade.tpo.ecommerce.dto.body.OrderBody;
import com.example.uade.tpo.ecommerce.dto.request.OrderRequest;
import com.example.uade.tpo.ecommerce.entities.Artwork;
import com.example.uade.tpo.ecommerce.entities.Order;
import com.example.uade.tpo.ecommerce.entities.User;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.exceptions.InvalidOperationException;
import com.example.uade.tpo.ecommerce.exceptions.InvalidQuantityException;
import com.example.uade.tpo.ecommerce.exceptions.NotFoundException;
import com.example.uade.tpo.ecommerce.services.ArtworkService;
import com.example.uade.tpo.ecommerce.services.OrderService;
import com.example.uade.tpo.ecommerce.services.UserService;

@RestController
@RequestMapping("order")
public class OrderController {
  @Autowired
  private UserService userService;

  @Autowired
  private ArtworkService artworkService;

  @Autowired
  private OrderService orderService;

  @GetMapping("/{userId}")
  public ResponseEntity<Set<Order>> getOrders(@PathVariable Long userId) throws NotFoundException {
    Optional<User> user = userService.getUserById(userId);
    if (!user.isPresent()) {
      throw new NotFoundException("El Usuario no existe:\n { userId: " + userId + " }");
    }

    return ResponseEntity.ok(orderService.getUserOrders(user.get()));
  }

  @GetMapping("/{userId}/cart")
  public ResponseEntity<Set<Order>> getCartOrders(@PathVariable Long userId) throws NotFoundException {
    Optional<User> user = userService.getUserById(userId);
    if (!user.isPresent()) {
      throw new NotFoundException("El Usuario no existe:\n { userId: " + userId + " }");
    }

    return ResponseEntity.ok(orderService.getCartOrders(user.get()));
  }

  @GetMapping("/{userId}/bought")
  public ResponseEntity<Set<Order>> getBoughtOrders(@PathVariable Long userId) throws NotFoundException {
    Optional<User> user = userService.getUserById(userId);
    if (!user.isPresent()) {
      throw new NotFoundException("El Usuario no existe:\n { userId: " + userId + " }");
    }

    return ResponseEntity.ok(orderService.getBoughtOrders(user.get()));
  }

  @PostMapping
  public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest)
      throws NotFoundException, InvalidQuantityException, DuplicateException, InvalidOperationException {
    Optional<User> user = userService.getUserById(orderRequest.getUserId());
    if (!user.isPresent()) {
      throw new NotFoundException("El Usuario no existe:\n { userId: " + orderRequest.getUserId() + " }");
    }

    Optional<Artwork> artwork = artworkService.getArtworkById(orderRequest.getArtworkId());
    if (!artwork.isPresent()) {
      throw new NotFoundException("El Artwork no existe:\n { artworkId: " + orderRequest.getArtworkId() + " }");
    }

    if (orderRequest.getQuantity() <= 0) {
      throw new InvalidQuantityException("Cantidad solicitada invalida.");
    }

    OrderBody body = OrderBody.builder().user(user.get()).artwork(artwork.get()).quantity(orderRequest.getQuantity())
        .build();
    Order order = orderService.createOrder(body);
    return ResponseEntity.ok(order);
  }

  @PutMapping("/{orderId}")
  public ResponseEntity<Order> updateOrder(@PathVariable Long orderId, @RequestBody OrderRequest orderRequest)
      throws NotFoundException, InvalidQuantityException, DuplicateException, InvalidOperationException {
    Optional<Order> order = orderService.getOrderById(orderId);
    if (!order.isPresent()) {
      throw new NotFoundException("La Order no existe:\n { orderId: " + orderId + " }");
    }

    Optional<User> user = userService.getUserById(orderRequest.getUserId());
    if (!user.isPresent()) {
      throw new NotFoundException("El Usuario no existe:\n" + orderRequest.toString());
    }

    Optional<Artwork> artwork = artworkService.getArtworkById(orderRequest.getArtworkId());
    if (!artwork.isPresent()) {
      throw new NotFoundException("El Artwork no existe:\n" + orderRequest.toString());
    }

    if (orderRequest.getQuantity() <= 0) {
      throw new InvalidQuantityException("Cantidad solicitada invalida.");
    }

    OrderBody body = OrderBody.builder().user(user.get()).artwork(artwork.get()).quantity(orderRequest.getQuantity())
        .build();
    Order newOrder = orderService.updateOrder(order.get(), body);

    return ResponseEntity.ok(newOrder);
  }

  @DeleteMapping("/{orderId}")
  public ResponseEntity<Void> removeCartItem(@PathVariable Long orderId)
      throws NotFoundException {
    Optional<Order> order = orderService.getOrderById(orderId);
    if (!order.isPresent()) {
      throw new NotFoundException("La Order no existe:\n { orderId: " + orderId + " }");
    }

    orderService.deleteOrder(order.get());

    return ResponseEntity.ok().build();
  }

  @PostMapping("/{orderId}/buy")
  public ResponseEntity<Order> buyOrder(@PathVariable Long orderId)
      throws NotFoundException, InvalidOperationException {
    Optional<Order> order = orderService.getOrderById(orderId);
    if (!order.isPresent()) {
      throw new NotFoundException("La Order no existe:\n { orderId: " + orderId + " }");
    }

    Order newOrder = orderService.buyOrder(order.get());

    return ResponseEntity.ok(newOrder);
  }
}
