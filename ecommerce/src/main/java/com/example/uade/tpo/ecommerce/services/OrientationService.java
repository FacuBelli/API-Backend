package com.example.uade.tpo.ecommerce.services;

import java.util.List;
import java.util.Optional;

import com.example.uade.tpo.ecommerce.dto.body.OrientationBody;
import com.example.uade.tpo.ecommerce.entities.Orientation;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.exceptions.NotFoundException;

public interface OrientationService {
  public List<Orientation> getOrientations();

  public Optional<Orientation> getOrientationById(Long id);

  public Optional<Orientation> getOrientationByName(String name);

  public Orientation createOrientation(OrientationBody body) throws DuplicateException;

  public Orientation updateOrientation(Orientation orientation, OrientationBody orientationBody);

  public void deleteOrientation(Orientation orientation) throws NotFoundException;
}
