package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Models.Playlist;

import java.util.List;

public interface PlaylistService {

    List<Playlist> getAllPlaylist ();


    Playlist getPlaylist (Long id);


    void insertPlaylist(Playlist playlist);


    void updatePlaylist(Playlist playlist);


    Long deletePlaylist(Long id);
}
