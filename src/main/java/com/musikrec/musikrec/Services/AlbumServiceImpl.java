package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Dto.Responses.AlbumDetailsDto;
import com.musikrec.musikrec.Dto.Responses.AlbumDto;
import com.musikrec.musikrec.Dto.Responses.SongResponseDto;
import com.musikrec.musikrec.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.musikrec.musikrec.Models.Album;
import com.musikrec.musikrec.Models.Song;
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
    public List<AlbumDto> getAllAlbums() {
        List<Album> albums = albumRepository.findAll();
        List<AlbumDto> response = new ArrayList<>();

        for(Album a : albums){
            AlbumDto dto = new AlbumDto();

            dto.setId(a.getId());
            dto.setName(a.getTitle());
            dto.setImageUrl(a.getImageUrl());

            response.add(dto);
        }

        return response;
    }


    @Override
    public AlbumDetailsDto getAlbum(Integer albumId) {

        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new ResourceNotFoundException("Album with id: " + albumId + " not found."));

        AlbumDetailsDto response = new AlbumDetailsDto();
        List<SongResponseDto> songList = new ArrayList<>();

        response.setId(albumId);
        response.setImageUrl(album.getImageUrl());
        response.setName(album.getTitle());

        for (Song s : album.getSongs()){
            SongResponseDto dto = new SongResponseDto();

            dto.setId(s.getId());
            dto.setName(s.getTitle());
            dto.setAlbum(album.getTitle());
            dto.setArtist(s.getArtist());

            songList.add(dto);
        }

        response.setSongs(songList);

        return response;
    }
}
