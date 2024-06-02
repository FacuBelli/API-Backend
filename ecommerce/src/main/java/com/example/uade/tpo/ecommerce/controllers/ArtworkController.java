package com.example.uade.tpo.ecommerce.controllers;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.uade.tpo.ecommerce.dto.body.ArtworkBody;
import com.example.uade.tpo.ecommerce.dto.request.ArtworkRequest;
import com.example.uade.tpo.ecommerce.entities.Artwork;
import com.example.uade.tpo.ecommerce.entities.Category;
import com.example.uade.tpo.ecommerce.entities.Orientation;
import com.example.uade.tpo.ecommerce.entities.Style;
import com.example.uade.tpo.ecommerce.entities.Theme;
import com.example.uade.tpo.ecommerce.entities.User;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.exceptions.NotFoundException;
import com.example.uade.tpo.ecommerce.services.ArtworkService;
import com.example.uade.tpo.ecommerce.services.CategoryService;
import com.example.uade.tpo.ecommerce.services.UserService;
import com.example.uade.tpo.ecommerce.services.OrientationService;
import com.example.uade.tpo.ecommerce.services.StyleService;
import com.example.uade.tpo.ecommerce.services.ThemeService;

@RestController
@RequestMapping("artwork")
public class ArtworkController {
  @Autowired
  private ArtworkService artworkService;

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private UserService userService;

  @Autowired
  private OrientationService orientationService;

  @Autowired
  private StyleService styleService;

  @Autowired
  private ThemeService themeService;

  @GetMapping
  public ResponseEntity<List<Artwork>> getCategories() {
    return ResponseEntity.ok(artworkService.getArtworks());
  }

  @GetMapping("/{artworkId}")
  public ResponseEntity<Artwork> getArtworkById(@PathVariable Long artworkId) {
    Optional<Artwork> result = artworkService.getArtworkById(artworkId);
    if (result.isPresent())
      return ResponseEntity.ok(result.get());
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<Object> createArtwork(@RequestBody ArtworkRequest artworkRequest)
      throws DuplicateException, NotFoundException {
    Optional<User> artist = userService.getUserById(artworkRequest.getArtistId());
    if (!artist.isPresent()) {
      throw new NotFoundException("El Usuario(id): '" + artworkRequest.getArtistId() + "' no existe.");
    }

    Set<Category> categories = new HashSet<Category>();
    for (String categoryString : artworkRequest.getCategories()) {
      Optional<Category> category = categoryService.getCategoryByName(categoryString);
      if (!category.isPresent()) {
        throw new NotFoundException("La Category: '" + categoryString + "' no existe.");
      }
      categories.add(category.get());
    }

    Optional<Orientation> orientation = orientationService.getOrientationByName(artworkRequest.getOrientation());
    if (!orientation.isPresent()) {
      throw new NotFoundException("La Orientation: '" + artworkRequest.getOrientation() + "' no existe.");
    }

    Set<Style> styles = new HashSet<Style>();
    for (String styleString : artworkRequest.getStyles()) {
      Optional<Style> style = styleService.getStyleByName(styleString);
      if (!style.isPresent()) {
        throw new NotFoundException("El Style: '" + styleString + "' no existe.");
      }
      styles.add(style.get());
    }

    Set<Theme> themes = new HashSet<Theme>();
    for (String themeString : artworkRequest.getThemes()) {
      Optional<Theme> theme = themeService.getThemeByName(themeString);
      if (!theme.isPresent()) {
        throw new NotFoundException("El Theme: '" + themeString + "' no existe.");
      }
      themes.add(theme.get());
    }

    ArtworkBody body = ArtworkBody.builder()
        .artist(artist.get())
        .categories(categories)
        .description(artworkRequest.getDescription())
        .isHidden(artworkRequest.isHidden())
        .image(artworkRequest.getImage())
        .orientation(orientation.get())
        .price(artworkRequest.getPrice())
        .stock(artworkRequest.getStock())
        .styles(styles)
        .themes(themes)
        .title(artworkRequest.getTitle())
        .build();

    Artwork result = artworkService.createArtwork(body);

    return ResponseEntity.created(URI.create("/artwork/" + result.getId())).body(result);
  }
}
