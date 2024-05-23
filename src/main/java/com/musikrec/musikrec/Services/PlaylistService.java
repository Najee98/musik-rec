package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Dto.Responses.PlaylistResponseDto;
import com.musikrec.musikrec.Dto.Requests.PlaylistRequestDto;
import com.musikrec.musikrec.Models.Playlist;
import java.util.List;

public interface PlaylistService {

    Playlist getPlaylist (Long userId, Long playlistId);


    Playlist insertPlaylist(PlaylistRequestDto request);


    void updatePlaylist(Playlist playlist);


    Long deletePlaylist(Long userId, Long playlistId);


    List<PlaylistResponseDto> getAllPlaylistsForUser(Long userId);
}
