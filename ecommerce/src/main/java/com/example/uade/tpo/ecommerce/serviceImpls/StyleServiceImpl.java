package com.example.uade.tpo.ecommerce.serviceImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.ecommerce.dto.body.StyleBody;
import com.example.uade.tpo.ecommerce.entities.Style;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.repositories.StyleRepository;
import com.example.uade.tpo.ecommerce.services.StyleService;

@Service
public class StyleServiceImpl implements StyleService {
  @Autowired
  private StyleRepository styleRepository;

  public List<Style> getStyles() {
    return styleRepository.findAll();
  }

  public Optional<Style> getStyleById(Long id) {
    return styleRepository.findById(id);
  }

  public Optional<Style> getStyleByName(String name) {
    return styleRepository.findByName(name);
  }  

  public Style createStyle(StyleBody body) throws DuplicateException {
    Optional<Style> style = styleRepository.findByName(body.getName());
    if (style.isPresent())
      throw new DuplicateException("El Style ya existe.");
    return styleRepository.save(new Style(body));
  }

  public Style updateStyle(Style Style, StyleBody StyleBody) {
    throw new UnsupportedOperationException("Unimplemented method 'updateStyle'");
  }

  public void deleteStyle(Style Style) {
    throw new UnsupportedOperationException("Unimplemented method 'deleteStyle'");
  }
}
