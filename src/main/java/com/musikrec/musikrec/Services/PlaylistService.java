package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Dto.Responses.PlaylistResponseDto;
import com.musikrec.musikrec.Dto.Requests.PlaylistRequestDto;
import com.musikrec.musikrec.Models.Playlist;
import com.musikrec.musikrec.Models.Song;

import java.util.List;

public interface PlaylistService {

    List<Playlist> getAllPlaylist ();


    Playlist getPlaylist (Long id);


    Playlist insertPlaylist(PlaylistRequestDto request);


    void updatePlaylist(Playlist playlist);

    Long deletePlaylist(Long id);

    List<Song> getAllSongsFromPlaylist(Long playlistId);

    List<PlaylistResponseDto> getAllPlaylistsForUser(Long userId);
}
