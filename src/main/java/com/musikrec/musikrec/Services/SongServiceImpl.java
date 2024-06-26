package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Dto.Responses.SongDetailsResponseDto;
import com.musikrec.musikrec.Dto.Responses.SongResponseDto;
import com.musikrec.musikrec.Dto.Responses.SongSearchResponse;
import com.musikrec.musikrec.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.musikrec.musikrec.Models.Playlist;
import com.musikrec.musikrec.Models.Song;
import com.musikrec.musikrec.Repositories.PlaylistRepository;
import com.musikrec.musikrec.Repositories.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    private final PlaylistRepository playlistRepository;

    @Override
    public List<SongResponseDto> getAllSongs() {
        List<Song> songs = songRepository.findAll();

        List<SongResponseDto> responseList = new ArrayList<>();

        for (Song s : songs) {
            SongResponseDto response = new SongResponseDto();
            response.setId(s.getId());
            response.setName(s.getTitle());
            response.setArtist(s.getArtist());

            responseList.add(response);
        }

        return responseList;
    }

    @Override
    public SongDetailsResponseDto getSong(Integer id) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Song Not Found"));

        SongDetailsResponseDto responseList = new SongDetailsResponseDto();

        responseList.setId(song.getId());
        responseList.setTitle(song.getTitle());
        responseList.setArtist(song.getArtist());
        responseList.setGenre(song.getGenre());
        responseList.setReleaseYear(song.getReleaseYear());
        responseList.setAlbumId(song.getAlbum().getId());

        return responseList;

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
    public List<SongSearchResponse> searchSong(String query) {

        List<SongSearchResponse> songs = songRepository.searchSongs(query);

        return songs;
    }
}
