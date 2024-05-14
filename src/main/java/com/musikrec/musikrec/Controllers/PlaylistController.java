package com.musikrec.musikrec.Controllers;


import com.musikrec.musikrec.Models.Playlist;
import com.musikrec.musikrec.Services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping()
    public ResponseEntity<List<Playlist>> getAllPlaylist() {
        return ResponseEntity.ok(playlistService.getAllPlaylist());
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Playlist> getPlaylist(@PathVariable Long id) {
        return ResponseEntity.ok(playlistService.getPlaylist(id));
    }


    @PostMapping("/add")
    public ResponseEntity<Playlist> insertPlaylist(@RequestBody Playlist playlist) {
        return ResponseEntity.ok(playlistService.insertPlaylist(playlist));
    }


    @PutMapping("/update")
    public void updatePlaylist(@RequestBody Playlist playlist) {
        playlistService.updatePlaylist(playlist);
    }


    @DeleteMapping("/delete")
    public void deletePlaylist(@RequestParam Long id) {
        playlistService.deletePlaylist(id);
    }

}
