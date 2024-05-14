package com.musikrec.musikrec.Services;


import com.musikrec.musikrec.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.musikrec.musikrec.Models.Playlist;
import com.musikrec.musikrec.Repositories.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImp implements PlaylistService {

    private final PlaylistRepository playlistRepository;

    @Override
    public List<Playlist> getAllPlaylist() {
        return playlistRepository.findAll();
    }

    @Override
    public Playlist getPlaylist(Long id) {
        return playlistRepository.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException("playlist not found"));
    }

    @Override
    public void insertPlaylist(Playlist playlist) {
        Playlist newPlaylist = new Playlist();

        newPlaylist.setName(playlist.getName());
        newPlaylist.setDescription(playlist.getDescription());

        playlistRepository.save(newPlaylist);

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
}
