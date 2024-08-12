package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Dto.Responses.SongDetailsResponseDto;
import com.musikrec.musikrec.Dto.Responses.SongResponseDto;
import com.musikrec.musikrec.Dto.Responses.SongSearchResponse;
import com.musikrec.musikrec.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.musikrec.musikrec.Integration.SpotifyAPI.ApiResponse.SpotifySearchResponse;
import com.musikrec.musikrec.Integration.SpotifyAPI.SpotifyService;
import com.musikrec.musikrec.Models.Playlist;
import com.musikrec.musikrec.Models.Song;
import com.musikrec.musikrec.Repositories.PlaylistRepository;
import com.musikrec.musikrec.Repositories.SongRepository;
import com.musikrec.musikrec.User.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    private final PlaylistRepository playlistRepository;

    private final UserService userService;

    private final SpotifyService spotifyService;

    @Override
    public List<SongResponseDto> getAllSongs() {
        List<Song> songs = songRepository.findAllSongs();

        List<SongResponseDto> responseList = new ArrayList<>();

        for (Song s : songs) {
            SongResponseDto response = new SongResponseDto();
            response.setId(s.getId());
            response.setName(s.getTitle());
            response.setArtist(s.getArtist().getName());
            response.setImageUrl(s.getImageUrl());
            response.setPreviewUrl(s.getPreviewUrl());

            responseList.add(response);
        }

        return responseList;
    }

    @Override
    public SongDetailsResponseDto getSong(Integer id) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Song Not Found"));

        SongDetailsResponseDto response = new SongDetailsResponseDto();

        response.setId(song.getId());
        response.setTitle(song.getTitle());
        response.setArtist(song.getArtist().getName());
//        response.setGenre(song.getGenre());
        response.setReleaseYear(song.getReleaseYear());
        response.setAlbumId(song.getAlbum().getId());
        response.setPreviewUrl(song.getPreviewUrl());
        response.setImageUrl(song.getImageUrl());

        return response;

    }

    @Override
    public void addSongToPlaylist(Integer songId, Integer playlistId) {
        // Search For Songs
        Optional<Song> song = Optional.ofNullable(songRepository.findById(songId)
                .orElseThrow(() -> new ResourceNotFoundException("Song not found!")));

        // Search For Playlist
        Optional<Playlist> playlist = Optional.ofNullable(playlistRepository.findById(playlistId)
                .orElseThrow(() -> new ResourceNotFoundException("Playlist not found!")));

        // Add The Song From The Playlist
        playlist.get().addSong(song.get());

        // Save Changes
        playlistRepository.save(playlist.get());

    }
    @Override
    public void removeSongFromPlaylist(Integer songId, Integer playlistId) {
        // Search For Songs
        Optional<Song> song =Optional.ofNullable(songRepository.findById(songId)
                .orElseThrow(() -> new ResourceNotFoundException("Song not found!")));

        // Search For Playlist
        Optional<Playlist> playlist =Optional.ofNullable(playlistRepository.findById(playlistId)
                .orElseThrow(() -> new ResourceNotFoundException("Playlist not found!")));

        // Remove The Song From The Playlist
        playlist.get().removeSong(song.get());

        // Save Changes
        playlistRepository.save(playlist.get());
    }

    @Override
    public List<SongResponseDto> searchSong(String query) {

        try{
            List<SongResponseDto> result = songRepository.searchSongs(query);
            return result;

        }catch (ResourceNotFoundException e){
            throw e;
        }
  //      SpotifySearchResponse spotifyResults = spotifyService.searchTracks(query);

//        for(SpotifySearchResponse s : spotifyResults){
//
//            SongSearchResponse song = new SongSearchResponse();
//
//            int i = 0;
//
//            song.setTitle(s.getTracks().getItems().get(i).getName());
//            song.setArtist(s.getTracks().getItems().get(i).getArtists().get(i).getName());
//            song.setAlbum(s.getTracks().getItems().get(i).getAlbum().getName());
//            song.setImageUrl(s.getTracks().getItems().get(i).getAlbum().getImages().get(i).getUrl());
//            song.setPreviewUrl(s.getTracks().getItems().get(i).getPreview_url());
//
//        }
    }

    @Override
    public List<SongResponseDto> getUserHistory() {
        AppUser user = userService.getUserFromLogin();
        List<Song> songs = songRepository.findSongHistoryByUserId(user.getId());
        List<SongResponseDto> response = new ArrayList<>();

        for (Song s : songs) {
            SongResponseDto dto = new SongResponseDto();

            dto.setId(s.getId());
            dto.setName(s.getTitle());
            dto.setAlbum(s.getArtist().getName());
            dto.setAlbum(s.getAlbum().getTitle());
            dto.setPreviewUrl(s.getPreviewUrl());
            dto.setImageUrl(s.getImageUrl());

            response.add(dto);
        }

        return response;

    }

    @Override
    public void insertHistory(Integer songId) {
        Song song = songRepository.findByIdWithUsers(songId)
                .orElseThrow(() -> new ResourceNotFoundException("Song with id: " + songId + " not found"));

        AppUser user = userService.getUserFromLogin();

        // Add the user to the song's users list
        if (!song.getUsers().contains(user)) {
            song.getUsers().add(user);
        }

        // Add the song to the user's song history list
        if (!user.getUserSongs().contains(song)) {
            user.getUserSongs().add(song);
        }

        // Saving the user will also save the relationship, no need to save song explicitly again
        userService.saveUser(user);

        //Hibernate.initialize(song.getUsers());
    }

}
