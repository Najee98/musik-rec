package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.musikrec.musikrec.Models.Album;
import com.musikrec.musikrec.Repositories.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService{

    private final AlbumRepository albumRepository;


    @Override
    public List<Album> getAllAlbumsForArtist(String artist) {

        List<Album> albums = albumRepository.getAllAlbumsForArtist(artist);

                if(albums.isEmpty())
                    throw new ResourceNotFoundException("No albums found for artist: " + artist);
                else
                    return albums;
    }


    @Override
    public Album getAlbumDetails(String title) {
        Album response = albumRepository.getAlbumDetails(title);

        if (response == null)
            throw new ResourceNotFoundException("Album doesn't exist");

        return response;
    }
}
