package com.example.uade.tpo.ecommerce.services;

import java.util.List;
import java.util.Optional;

import com.example.uade.tpo.ecommerce.entities.Style;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;

public interface StyleService {
  public List<Style> getStyles();

  public Optional<Style> getStyleById(Long id);

  public Style createStyle(String name) throws DuplicateException;
}
