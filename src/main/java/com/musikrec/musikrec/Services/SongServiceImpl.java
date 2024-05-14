package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.musikrec.musikrec.Models.Playlist;
import com.musikrec.musikrec.Models.Song;
import com.musikrec.musikrec.Repositories.PlaylistRepository;
import com.musikrec.musikrec.Repositories.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    private final PlaylistRepository playlistRepository;

    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @Override
    public Song getSong(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Song not found"));
    }

    @Override
    public Song insertSong(Song song) {
        Song newSong = new Song();

        newSong.setAlbum(song.getAlbum());
        newSong.setTitle(song.getTitle());
        newSong.setArtist(song.getArtist());

        return songRepository.save(newSong);
    }

    @Override
    public Song updateSong(Song song) {

        Optional<Song> songOptional = songRepository.findById(song.getId());

        if(songOptional.isPresent()){
            songOptional.get().setGenre(song.getGenre());
            songOptional.get().setArtist(song.getArtist());
            songOptional.get().setTitle(song.getTitle());

            return songRepository.save(songOptional.get());
        }else{
            throw new ResourceNotFoundException("Song not found");
        }

    }

    @Override
    public Long deleteSong(Long id) {
        Optional<Song> songOptional = songRepository.findById(id);

        if (songOptional.isEmpty())
            throw new ResourceNotFoundException("Song not found!");
        else {
            songRepository.deleteById(id);
            return id;
        }
    }

    @Override
    public void addSongToPlaylist(Long songId, Long playlistId) {
        Optional<Song> song = Optional.ofNullable(songRepository.findById(songId)
                .orElseThrow(() -> new ResourceNotFoundException("Song not found!")));

        Optional<Playlist> playlist = Optional.ofNullable(playlistRepository.findById(playlistId)
                .orElseThrow(() -> new ResourceNotFoundException("Playlist not found!")));

        playlist.get().addSong(song.get());

        playlistRepository.save(playlist.get());

    }
}
