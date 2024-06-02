package com.example.uade.tpo.ecommerce.serviceImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.ecommerce.repositories.ThemeRepository;
import com.example.uade.tpo.ecommerce.services.ThemeService;
import com.example.uade.tpo.ecommerce.dto.body.ThemeBody;
import com.example.uade.tpo.ecommerce.entities.Theme;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;

@Service
public class ThemeServiceImpl implements ThemeService {
  @Autowired
  private ThemeRepository themeRepository;

  public List<Theme> getThemes() {
    return themeRepository.findAll();
  }

  public Optional<Theme> getThemeById(Long id) {
    return themeRepository.findById(id);
  }

  public Optional<Theme> getThemeByName(String name) {
    return themeRepository.findByName(name);
  }

  public Theme createTheme(ThemeBody body) throws DuplicateException {
    Optional<Theme> theme = themeRepository.findByName(body.getName());
    if (theme.isPresent())
      throw new DuplicateException("El Theme ya existe.");
    return themeRepository.save(new Theme(body));
  }

  @Override
  public Theme updateTheme(Theme Theme, ThemeBody ThemeBody) {
    throw new UnsupportedOperationException("Unimplemented method 'updateTheme'");
  }

  @Override
  public void deleteTheme(Theme Theme) {
    throw new UnsupportedOperationException("Unimplemented method 'deleteTheme'");
  }
}
