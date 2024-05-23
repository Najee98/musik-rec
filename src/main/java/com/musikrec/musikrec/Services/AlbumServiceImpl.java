package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.musikrec.musikrec.Models.Album;
import com.musikrec.musikrec.Repositories.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService{

    private final AlbumRepository albumRepository;


    @Override
    public List<Album> getAllAlbums(String artist) {

        List<Album> albums = albumRepository.getAllAlbums(artist);

                if(albums.isEmpty())
                    throw new ResourceNotFoundException("No albums found for artist: " + artist);
                else
                    return albums;
    }
}
