package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.musikrec.musikrec.Models.Artist;
import com.musikrec.musikrec.Repositories.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService{

    private final ArtistRepository artistRepository;

    @Override
    public Artist getArtistByName(String name) {
        Artist artist = artistRepository.findByName(name);

        if (artist == null)
            throw new ResourceNotFoundException("Artist with name " + name + " not found!");

        return artist;
    }
}
