package com.example.uade.tpo.ecommerce.serviceImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.uade.tpo.ecommerce.dto.body.ArtworkBody;
import com.example.uade.tpo.ecommerce.entities.Artwork;
import com.example.uade.tpo.ecommerce.exceptions.DuplicateException;
import com.example.uade.tpo.ecommerce.repositories.ArtworkRepository;
import com.example.uade.tpo.ecommerce.services.ArtworkService;

@Service
public class ArtworkServiceImpl implements ArtworkService {
  @Autowired
  private ArtworkRepository artworkRepository;

  public List<Artwork> getArtworks() {
    return artworkRepository.findAll();
  }

  public List<Artwork> getArtworksByUserId(Long id) {
    return artworkRepository.findAllByUserId(id);
  }

  public Optional<Artwork> getArtworkById(Long id) {
    return artworkRepository.findById(id);
  }

  public Artwork createArtwork(ArtworkBody body) throws DuplicateException {
    return artworkRepository.save(new Artwork(body));
  }

  public Artwork updateArtwork(Artwork artwork, ArtworkBody body) {
    if (body.getArtist() != null && !body.getArtist().equals(artwork.getArtist())) {
      artwork.setArtist(body.getArtist());
    }

    if (body.getCategories() != null && !body.getCategories().equals(artwork.getCategories())) {
      artwork.setCategories(body.getCategories());
    }

    if (body.getDescription() != null && !body.getDescription().equals(artwork.getDescription())) {
      artwork.setDescription(body.getDescription());
    }

    if (body.getImage() != null && !body.getImage().equals(artwork.getImage())) {
      artwork.setImage(body.getImage());
    }

    if (body.getOrientation() != null && !body.getOrientation().equals(artwork.getOrientation())) {
      artwork.setOrientation(body.getOrientation());
    }

    if (body.getPrice() != null && !body.getPrice().equals(artwork.getPrice())) {
      artwork.setPrice(body.getPrice());
    }

    if (body.getStock() != null && !body.getStock().equals(artwork.getStock())) {
      artwork.setStock(artwork.getStock());
    }

    if (body.getStyles() != null && !body.getStyles().equals(artwork.getStyles())) {
      artwork.setStyles(body.getStyles());
    }

    if (body.getThemes() != null && !body.getThemes().equals(artwork.getThemes())) {
      artwork.setThemes(body.getThemes());
    }

    if (body.getTitle() != null && !body.getTitle().equals(artwork.getTitle())) {
      artwork.setTitle(body.getTitle());
    }

    if (body.getDiscount() != null && !body.getDiscount().equals(artwork.getDiscount())) {
      artwork.setDiscount(body.getDiscount());
    }

    return artworkRepository.save(artwork);
  }

  public void deleteArtwork(Artwork artwork) {
    artworkRepository.delete(artwork);
  }

  public void incrementStock(Artwork artwork, Integer amount) {
    artwork.setStock(artwork.getStock() + amount);

    artworkRepository.save(artwork);
  }
  
  public void decrementStock(Artwork artwork, Integer amount) {
    artwork.setStock(artwork.getStock() - amount);

    artworkRepository.save(artwork);
  }
}
