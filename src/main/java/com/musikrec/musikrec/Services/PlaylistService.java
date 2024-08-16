package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Dto.Requests.PlaylistUpdateRequest;
import com.musikrec.musikrec.Dto.Responses.PlaylistDetailsResponseDto;
import com.musikrec.musikrec.Dto.Responses.PlaylistResponseDto;
import com.musikrec.musikrec.Dto.Requests.PlaylistRequestDto;
import com.musikrec.musikrec.Models.Playlist;
import java.util.List;

public interface PlaylistService {

    PlaylistDetailsResponseDto getPlaylist(Integer playlistId);


    PlaylistResponseDto insertPlaylist(PlaylistRequestDto request);


    void updatePlaylist(Integer playlistId, PlaylistUpdateRequest request);


    Integer deletePlaylist(Integer playlistId);


    List<PlaylistDetailsResponseDto> getAllPlaylistsForUser();
}
