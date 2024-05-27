package com.example.uade.tpo.ecommerce.services;

import java.util.List;
import java.util.Optional;

import com.example.uade.tpo.ecommerce.dto.OrientationBody;
import com.example.uade.tpo.ecommerce.entities.Orientation;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;

public interface OrientationService {
  public List<Orientation> getOrientations();

  public Optional<Orientation> getOrientationById(Long id);

  public Orientation createOrientation(OrientationBody body) throws DuplicateException;
}
