package com.musikrec.musikrec.Controllers;


import com.musikrec.musikrec.Dto.Requests.PlaylistUpdateRequest;
import com.musikrec.musikrec.Dto.Responses.PlaylistDetailsResponseDto;
import com.musikrec.musikrec.Dto.Responses.PlaylistResponseDto;
import com.musikrec.musikrec.Dto.Requests.PlaylistRequestDto;
import com.musikrec.musikrec.Models.Playlist;
import com.musikrec.musikrec.Services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping()
    public ResponseEntity<List<PlaylistResponseDto>> getAllPlaylistForUser() {
        return ResponseEntity.ok(playlistService.getAllPlaylistsForUser());
    }


    @GetMapping("/get/{playlistId}")
    public ResponseEntity<PlaylistDetailsResponseDto> getPlaylist(@PathVariable Integer playlistId) {
        return ResponseEntity.ok(playlistService.getPlaylist(playlistId));
    }


    @PostMapping("/add")
    public ResponseEntity<Object> insertPlaylist(@RequestBody PlaylistRequestDto request) {
        playlistService.insertPlaylist(request);
        return new ResponseEntity<>("{ \"message\": \" Playlist added successfully \" }", HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<Object> updatePlaylist(
            @RequestParam Integer playlistId,
            @RequestBody PlaylistUpdateRequest request) {
        playlistService.updatePlaylist(playlistId, request);
        return new ResponseEntity<>("{ \"message\": \" Playlist modified successfully \" }", HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Object> deletePlaylist(@RequestParam Integer playlistId) {
        playlistService.deletePlaylist(playlistId);
        return new ResponseEntity<>("{ \"message\": \" Playlist deleted successfully \" }", HttpStatus.OK);
    }

}
