package com.example.uade.tpo.ecommerce.serviceImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.ecommerce.dto.body.OrientationBody;
import com.example.uade.tpo.ecommerce.entities.Orientation;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.repositories.OrientationRepository;
import com.example.uade.tpo.ecommerce.services.OrientationService;

@Service
public class OrientationServiceImpl implements OrientationService {
  @Autowired
  private OrientationRepository orientationRepository;

  public List<Orientation> getOrientations() {
    return orientationRepository.findAll();
  }

  public Optional<Orientation> getOrientationById(Long id) {
    return orientationRepository.findById(id);
  }

  public Optional<Orientation> getOrientationByName(String name) {
    return orientationRepository.findByName(name);
  }

  public Orientation createOrientation(OrientationBody body) throws DuplicateException {
    Optional<Orientation> orientation = orientationRepository.findByName(body.getName());
    if (orientation.isPresent())
      throw new DuplicateException("La Orientation ya existe.");
    return orientationRepository.save(new Orientation(body));
  }

  public Orientation updateOrientation(Orientation orientation, OrientationBody body) {
    if (body.getName() != null && !body.getName().equals(orientation.getName())) {
      orientation.setName(body.getName());
    }

    return orientationRepository.save(orientation);
  }

  public void deleteOrientation(Orientation orientation) {
    orientationRepository.delete(orientation);
  }
}
