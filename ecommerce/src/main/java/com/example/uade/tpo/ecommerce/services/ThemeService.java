package com.example.uade.tpo.ecommerce.services;

import java.util.List;
import java.util.Optional;

import com.example.uade.tpo.ecommerce.dto.ThemeBody;
import com.example.uade.tpo.ecommerce.entities.Theme;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;

public interface ThemeService {
  public List<Theme> getThemes();

  public Optional<Theme> getThemeById(Long id);

  public Theme createTheme(ThemeBody body) throws DuplicateException;
}