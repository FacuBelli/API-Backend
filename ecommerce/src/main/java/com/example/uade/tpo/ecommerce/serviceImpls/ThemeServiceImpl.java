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
import com.example.uade.tpo.ecommerce.exceptions.NotFoundException;

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
    public void deleteTheme(Long themeId) throws NotFoundException {
        if (!themeRepository.existsById(themeId)) {
            throw new NotFoundException("El Theme(id): " + themeId + " no existe.");
        }
        themeRepository.deleteById(themeId);
    }

    @Override
    public Theme updateTheme(Long themeId, ThemeBody body) throws NotFoundException {
        Optional<Theme> themeOpt = themeRepository.findById(themeId);
        if (!themeOpt.isPresent()) {
            throw new NotFoundException("El Theme(id): " + themeId + " no existe.");
        }
        Theme theme = themeOpt.get();
        theme.setName(body.getName());
        return themeRepository.save(theme);
    }
}
