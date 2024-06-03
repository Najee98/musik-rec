package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Dto.Requests.PlaylistUpdateRequest;
import com.musikrec.musikrec.Dto.Responses.PlaylistDetailsResponseDto;
import com.musikrec.musikrec.Dto.Responses.PlaylistResponseDto;
import com.musikrec.musikrec.Dto.Requests.PlaylistRequestDto;
import com.musikrec.musikrec.Models.Playlist;
import java.util.List;

public interface PlaylistService {

    PlaylistDetailsResponseDto getPlaylist(Long playlistId);


    Playlist insertPlaylist(PlaylistRequestDto request);


    void updatePlaylist(Long playlistId, PlaylistUpdateRequest request);


    Long deletePlaylist(Long playlistId);


    List<PlaylistResponseDto> getAllPlaylistsForUser(Long userId);
}
