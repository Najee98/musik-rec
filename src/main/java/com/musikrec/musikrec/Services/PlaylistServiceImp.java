package com.musikrec.musikrec.Services;


import com.musikrec.musikrec.Dto.Requests.PlaylistUpdateRequest;
import com.musikrec.musikrec.Dto.Responses.PlaylistDetailsResponseDto;
import com.musikrec.musikrec.Dto.Responses.PlaylistResponseDto;
import com.musikrec.musikrec.Dto.Requests.PlaylistRequestDto;
import com.musikrec.musikrec.Dto.Responses.SongResponseDto;
import com.musikrec.musikrec.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.musikrec.musikrec.Models.Playlist;
import com.musikrec.musikrec.Models.Song;
import com.musikrec.musikrec.Repositories.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class PlaylistServiceImp implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final UserService userService;

    @Override
    public PlaylistDetailsResponseDto getPlaylist(Integer playlistId) {

        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(()-> new ResourceNotFoundException("Playlist with id " + playlistId + " not found"));

        PlaylistDetailsResponseDto response = new PlaylistDetailsResponseDto();
        List<SongResponseDto> songList = new ArrayList<>();

        response.setId(playlist.getId());
        response.setName(playlist.getName());
        response.setDescription(playlist.getDescription());

        for (Song s : playlist.getSongs()) {
            SongResponseDto dto = new SongResponseDto();

            dto.setId(s.getId());
            dto.setName(s.getTitle());
            dto.setAlbum(s.getTitle());
            dto.setArtist(s.getArtist());

            songList.add(dto);
        }

        response.setSongs(songList);

        return response;
    }


    @Override
    public Playlist insertPlaylist(PlaylistRequestDto request) {

        Integer userId = userService.getUserFromLogin().getId();

        Playlist newPlaylist = new Playlist();

        newPlaylist.setName(request.getName());
        newPlaylist.setDescription(request.getDescription());
        newPlaylist.setAppUser(userService.getUserById(userId));

        return playlistRepository.save(newPlaylist);
    }


    @Override
    public void updatePlaylist(Integer playlistId, PlaylistUpdateRequest request) {

        Optional<Playlist> playlistOptional = playlistRepository.findById(playlistId);

        if(playlistOptional.isPresent()){
            playlistOptional.get().setName(request.getName());
            playlistOptional.get().setDescription(request.getDescription());

            playlistRepository.save(playlistOptional.get());
        }else{
            throw new ResourceNotFoundException("Playlist not found");
        }

    }


    @Override
    public Integer deletePlaylist(Integer playlistId) {
        Optional<Playlist> playlistOptional = playlistRepository.findById(playlistId);

        if (playlistOptional.isEmpty())
            throw new ResourceNotFoundException("playlist not found!");
        else {
            playlistRepository.deleteById(playlistId);
            return playlistId;
        }
    }



    @Override
    public List<PlaylistResponseDto> getAllPlaylistsForUser() {

        Integer userId = userService.getUserFromLogin().getId();

        List<PlaylistResponseDto> response = playlistRepository.getAllPlaylistsForUser(userId);

        if (response.isEmpty())
            throw new ResourceNotFoundException("No playlists for user.");
        else
            return response;

    }
}
