package com.example.uade.tpo.ecommerce.serviceImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.ecommerce.dto.StyleBody;
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

  public Style createStyle(StyleBody body) throws DuplicateException {
    List<Style> styles = styleRepository.findByName(body.getName());
    if (!styles.isEmpty())
      throw new DuplicateException("El Style ya existe.");
    return styleRepository.save(new Style(body));
  }
}
