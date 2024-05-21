package com.musikrec.musikrec.Controllers;


import com.musikrec.musikrec.Dto.Responses.PlaylistResponseDto;
import com.musikrec.musikrec.Dto.Requests.PlaylistRequestDto;
import com.musikrec.musikrec.Models.Playlist;
import com.musikrec.musikrec.Models.Song;
import com.musikrec.musikrec.Services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping()
    public ResponseEntity<List<PlaylistResponseDto>> getAllPlaylistForUser(@RequestParam Long userId) {
        return ResponseEntity.ok(playlistService.getAllPlaylistsForUser(userId));
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Playlist> getPlaylist(@PathVariable Long id) {
        return ResponseEntity.ok(playlistService.getPlaylist(id));
    }


    @PostMapping("/add")
    public ResponseEntity<Object> insertPlaylist(@RequestBody PlaylistRequestDto request) {
        playlistService.insertPlaylist(request);
        return new ResponseEntity<>("{ \"message\": \" Playlist added successfully \" }", HttpStatus.OK);
    }


    @PutMapping("/update")
    public void updatePlaylist(@RequestBody Playlist playlist) {
        playlistService.updatePlaylist(playlist);
    }


    @DeleteMapping("/delete")
    public void deletePlaylist(@RequestParam Long id) {
        playlistService.deletePlaylist(id);
    }


    @GetMapping("/get-all-song-from-playlist")
    public ResponseEntity<List<Song>> getAllSongFromPlaylist(@RequestParam Long playlistId) {

        List<Song> songs = playlistService.getAllSongsFromPlaylist(playlistId);

        return new ResponseEntity<>(songs , HttpStatus.OK);
    }

}
