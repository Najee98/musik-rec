package com.musikrec.musikrec.Services;


import com.musikrec.musikrec.Models.Playlist;
import com.musikrec.musikrec.Repositories.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImp implements PlaylistService {

    private final PlaylistRepository playlistRepository;


    public Playlist getPlaylistByName(String Playlist_name)
    {
        return playlistRepository.findByPlaylist (Playlist_name);
    }
}
