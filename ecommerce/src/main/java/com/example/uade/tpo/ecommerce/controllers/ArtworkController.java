package com.example.uade.tpo.ecommerce.controllers;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  public ResponseEntity<Artwork> getArtworkById(@PathVariable Long artworkId) throws NotFoundException {
    Optional<Artwork> artwork = artworkService.getArtworkById(artworkId);
    if (!artwork.isPresent()) {
      throw new NotFoundException("El Artwork(id): " + artworkId + " no existe.");
    }

    return ResponseEntity.ok(artwork.get());
  }

  @PostMapping
  public ResponseEntity<Artwork> createArtwork(@RequestBody ArtworkRequest artworkRequest)
      throws DuplicateException, NotFoundException {
    Optional<User> artist = userService.getUserById(artworkRequest.getArtistId());
    if (!artist.isPresent()) {
      throw new NotFoundException("El Usuario(id): '" + artworkRequest.getArtistId() + "' no existe.");
    }

    Set<Category> categories = new HashSet<Category>();
    if (artworkRequest.getCategories() != null)
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
    if (artworkRequest.getStyles() != null)
      for (String styleString : artworkRequest.getStyles()) {
        Optional<Style> style = styleService.getStyleByName(styleString);
        if (!style.isPresent()) {
          throw new NotFoundException("El Style: '" + styleString + "' no existe.");
        }
        styles.add(style.get());
      }

    Set<Theme> themes = new HashSet<Theme>();
    if (artworkRequest.getThemes() != null)
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
        .discount(artworkRequest.getDiscount())
        .build();

    Artwork result = artworkService.createArtwork(body);

    return ResponseEntity.created(URI.create("/artwork/" + result.getId())).body(result);
  }

  @PutMapping("/{artworkId}")
  public ResponseEntity<Artwork> updateArtwork(@PathVariable Long artworkId,
      @RequestBody ArtworkRequest artworkRequest) throws NotFoundException {
    Optional<Artwork> artwork = artworkService.getArtworkById(artworkId);
    if (!artwork.isPresent()) {
      throw new NotFoundException("El Artwork(id): " + artworkId + " no existe.");
    }

    Optional<User> artist = userService.getUserById(artworkRequest.getArtistId());
    if (!artist.isPresent() && artworkRequest.getArtistId() != null) {
      throw new NotFoundException("El Usuario(id): '" + artworkRequest.getArtistId() + "' no existe.");
    }

    Set<Category> categories = null;
    if (artworkRequest.getCategories() != null) {
      categories = new HashSet<Category>();
      for (String categoryString : artworkRequest.getCategories()) {
        Optional<Category> category = categoryService.getCategoryByName(categoryString);
        if (!category.isPresent()) {
          throw new NotFoundException("La Category: '" + categoryString + "' no existe.");
        }
        categories.add(category.get());
      }
    }

    Optional<Orientation> orientation = orientationService.getOrientationByName(artworkRequest.getOrientation());
    if (!orientation.isPresent() && artworkRequest.getOrientation() != null) {
      throw new NotFoundException("La Orientation: '" + artworkRequest.getOrientation() + "' no existe.");
    }

    Set<Style> styles = null;
    if (artworkRequest.getStyles() != null) {
      styles = new HashSet<Style>();
      for (String styleString : artworkRequest.getStyles()) {
        Optional<Style> style = styleService.getStyleByName(styleString);
        if (!style.isPresent()) {
          throw new NotFoundException("El Style: '" + styleString + "' no existe.");
        }
        styles.add(style.get());
      }
    }
    Set<Theme> themes = null;
    if (artworkRequest.getThemes() != null) {
      themes = new HashSet<Theme>();
      for (String themeString : artworkRequest.getThemes()) {
        Optional<Theme> theme = themeService.getThemeByName(themeString);
        if (!theme.isPresent()) {
          throw new NotFoundException("El Theme: '" + themeString + "' no existe.");
        }
        themes.add(theme.get());
      }
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

    Artwork newArtwork = artworkService.updateArtwork(artwork.get(), body);

    return ResponseEntity.ok(newArtwork);
  }

  @DeleteMapping("/{artworkId}")
  public ResponseEntity<Void> deleteArtwork(@PathVariable Long artworkId) throws NotFoundException {
    Optional<Artwork> artwork = artworkService.getArtworkById(artworkId);
    if (!artwork.isPresent()) {
      throw new NotFoundException("El Artwork(id): " + artworkId + " no existe.");
    }

    artworkService.deleteArtwork(artwork.get());

    return ResponseEntity.ok().build();
  }

  @GetMapping("/{artworkId}/image")
  public ResponseEntity<byte[]> getArtworkImage(@PathVariable Long artworkId) {
    String base64Image = artworkService.getImageAsBase64(artworkId);
    if (base64Image != null) {
      byte[] imageBytes = Base64Utils.decodeFromString(base64Image);
      HttpHeaders headers = new HttpHeaders();
      headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg"); // or the appropriate image type
      return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
