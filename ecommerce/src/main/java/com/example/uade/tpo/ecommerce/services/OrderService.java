package com.example.uade.tpo.ecommerce.services;

import java.util.Optional;
import java.util.Set;

import com.example.uade.tpo.ecommerce.dto.body.OrderBody;
import com.example.uade.tpo.ecommerce.entities.Order;
import com.example.uade.tpo.ecommerce.entities.User;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.exceptions.InvalidOperationException;

public interface OrderService {
  public Set<Order> getUserOrders(User user);

  public Optional<Order> getOrderById(Long id);

  public Order createOrder(OrderBody body)
      throws DuplicateException, InvalidOperationException;

  public Order updateOrder(Order order, OrderBody body)
      throws InvalidOperationException;

  public void deleteOrder(Order order);

  public Order buyOrder(Order order) throws InvalidOperationException;

  public Set<Order> getCartOrders(User user);

  public Set<Order> getBoughtOrders(User user);
}
