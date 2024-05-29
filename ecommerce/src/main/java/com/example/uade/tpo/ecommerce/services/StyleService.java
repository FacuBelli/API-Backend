package com.example.uade.tpo.ecommerce.services;

import java.util.List;
import java.util.Optional;

import com.example.uade.tpo.ecommerce.dto.body.StyleBody;
import com.example.uade.tpo.ecommerce.entities.Style;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;

public interface StyleService {
  public List<Style> getStyles();

  public Optional<Style> getStyleById(Long id);

  public Optional<Style> getStyleByName(String name);

  public Style createStyle(StyleBody body) throws DuplicateException;
}
