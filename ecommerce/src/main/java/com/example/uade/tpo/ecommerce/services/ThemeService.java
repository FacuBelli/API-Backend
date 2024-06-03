package com.example.uade.tpo.ecommerce.services;

import java.util.List;
import java.util.Optional;

import com.example.uade.tpo.ecommerce.dto.body.ThemeBody;
import com.example.uade.tpo.ecommerce.entities.Theme;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.exceptions.NotFoundException;

public interface ThemeService {
  public List<Theme> getThemes();

  public Optional<Theme> getThemeById(Long id);

  public Optional<Theme> getThemeByName(String name);

  public Theme createTheme(ThemeBody body) throws DuplicateException;

  void deleteTheme(Long themeId) throws NotFoundException;
  Theme updateTheme(Long themeId, ThemeBody body) throws NotFoundException;
}
