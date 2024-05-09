package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Models.Playlist;

public interface PlaylistService {

    Playlist getPlaylistByName (String playlistName);
}
