package com.example.uade.tpo.ecommerce.serviceImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  public Orientation createOrientation(String name) throws DuplicateException {
    List<Orientation> orientations = orientationRepository.findByName(name);
    if (!orientations.isEmpty())
      throw new DuplicateException("La Orientation ya existe.");
    return orientationRepository.save(new Orientation(name));
  }
}
