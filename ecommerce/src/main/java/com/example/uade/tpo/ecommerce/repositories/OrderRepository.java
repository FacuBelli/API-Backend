package com.example.uade.tpo.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.uade.tpo.ecommerce.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  @Query(value = "select o from Order o where o.user.id = ?1")
  public List<Order> findAllByUserId(Long id);

  @Query(value = "select o from Order o where o.user.id = ?1 and o.isBought = true")
  public List<Order> findAllByUserIdIsBought(Long id);

  @Query(value = "select o from Order o where o.user.id = ?1 and o.isBought = false")
  public List<Order> findAllByUserIdIsNotBought(Long id);
}
