package com.example.uade.tpo.ecommerce.serviceImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import com.example.uade.tpo.ecommerce.repositories.ThemeRepository;
import com.example.uade.tpo.ecommerce.services.ThemeService;
import com.example.uade.tpo.ecommerce.dto.ThemeBody;
import com.example.uade.tpo.ecommerce.entities.Theme;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;

public class ThemeServiceImpl implements ThemeService {
@Autowired
  private ThemeRepository themeRepository;

  public List<Theme> getThemes() {
    return themeRepository.findAll();
  }

  public Optional<Theme> getThemeById(Long id) {
    return themeRepository.findById(id);
  }

  public Theme createTheme(ThemeBody body) throws DuplicateException {
    List<Theme> themes = themeRepository.findByName(body.getName());
    if (!themes.isEmpty())
      throw new DuplicateException("El Theme ya existe.");
    return themeRepository.save(new Theme(body));
  }
}
