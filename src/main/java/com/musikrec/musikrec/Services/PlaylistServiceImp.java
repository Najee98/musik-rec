package com.musikrec.musikrec.Services;


import com.musikrec.musikrec.Dto.Responses.PlaylistResponseDto;
import com.musikrec.musikrec.Dto.Requests.PlaylistRequestDto;
import com.musikrec.musikrec.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.musikrec.musikrec.Models.Playlist;
import com.musikrec.musikrec.Models.Song;
import com.musikrec.musikrec.Repositories.PlaylistRepository;
import com.musikrec.musikrec.User.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImp implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final AppUserRepository userRepository;

    @Override
    public PlaylistResponseDto getPlaylist(Long userId, Long playlistId) {
        PlaylistResponseDto response = playlistRepository.getPlaylist(userId, playlistId);

        if(response == null)
            throw new ResourceNotFoundException("Playlist doesn't exist");

        return response;
    }

    @Override
    public Playlist insertPlaylist(PlaylistRequestDto request) {
        Playlist newPlaylist = new Playlist();

        newPlaylist.setName(request.getName());
        newPlaylist.setDescription(request.getDescription());
        newPlaylist.setAppUser(
                userRepository.findById(request.getUserId().intValue())
                        .orElseThrow(() -> new ResourceNotFoundException("User doesn't exist.")));

        return playlistRepository.save(newPlaylist);
    }

    @Override
    public void updatePlaylist(Playlist playlist) {

        Optional<Playlist> playlistOptional = playlistRepository.findById(playlist.getId());

        if(playlistOptional.isPresent()){
            playlistOptional.get().setName(playlist.getName());
            playlistOptional.get().setDescription(playlist.getDescription());


            playlistRepository.save(playlistOptional.get());
        }else{
            throw new ResourceNotFoundException("Playlist not found");
        }

    }

    @Override
    public Long deletePlaylist(Long id) {
        Optional<Playlist> playlistOptional = playlistRepository.findById(id);

        if (playlistOptional.isEmpty())
            throw new ResourceNotFoundException("playlist not found!");
        else {
            playlistRepository.deleteById(id);
            return id;
        }
    }


    @Override
    public  List<Song> getAllSongsFromPlaylist(Long playlistId){

        Optional<Playlist> playlist = Optional.ofNullable(playlistRepository.findById(playlistId)
                .orElseThrow(() -> new ResourceNotFoundException("Playlist not found!")));

        return new ArrayList<>(playlist.get().getSongs());

    }

    @Override
    public List<PlaylistResponseDto> getAllPlaylistsForUser(Long userId) {

        List<PlaylistResponseDto> response = playlistRepository.getAllPlaylistsForUser(userId);

        if (response.isEmpty())
            throw new ResourceNotFoundException("No playlists for user.");
        else
            return response;

    }
}
