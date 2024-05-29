package com.example.uade.tpo.ecommerce.serviceImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.ecommerce.dto.body.UserBody;
import com.example.uade.tpo.ecommerce.entities.User;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
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
}
