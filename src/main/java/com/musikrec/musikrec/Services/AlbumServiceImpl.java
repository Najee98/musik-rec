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
    public List<Album> getAllAlbums(Long albumId) {

        List<Album> albums = albumRepository.getAllAlbums(albumId);

                if(albums.isEmpty())
                    throw new ResourceNotFoundException("No albums found");
                else
                    return albums;
    }


    @Override
    public Album getAlbum(Long albumId) {
        Album response = albumRepository.getAlbum(albumId);

        if (response == null)
            throw new ResourceNotFoundException("Album doesn't exist");

        return response;
    }
}
