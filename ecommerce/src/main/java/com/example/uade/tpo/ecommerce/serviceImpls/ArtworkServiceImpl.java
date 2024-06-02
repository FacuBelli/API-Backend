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
    return artworkRepository.findByUserId(id);
  }

  public Optional<Artwork> getArtworkById(Long id) {
    return artworkRepository.findById(id);
  }

  public Artwork createArtwork(ArtworkBody body) throws DuplicateException {
    return artworkRepository.save(new Artwork(body));
  }

  public Artwork updateArtwork(Artwork artwork, ArtworkBody body) {
    throw new UnsupportedOperationException("Unimplemented method 'updateArtwork'");
  }

  public void deleteArtwork(Artwork artwork) {
    throw new UnsupportedOperationException("Unimplemented method 'deleteArtwork'");
  }
}
