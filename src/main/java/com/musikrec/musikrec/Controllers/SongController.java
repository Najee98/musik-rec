package com.musikrec.musikrec.Controllers;

import com.musikrec.musikrec.Dto.Responses.SongDetailsResponseDto;
import com.musikrec.musikrec.Dto.Responses.SongResponseDto;
import com.musikrec.musikrec.Dto.Responses.SongSearchResponse;
import com.musikrec.musikrec.Models.Song;
import com.musikrec.musikrec.Services.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/songs")
public class SongController {

    private final SongService songService;

    @GetMapping()
    public ResponseEntity<List<SongResponseDto>> getAllSongs() {
        return ResponseEntity.ok(songService.getAllSongs());
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<SongDetailsResponseDto> getSong(@PathVariable Integer id) {
        return ResponseEntity.ok(songService.getSong(id));
    }


    @PostMapping("/add-to-playlist")
    public ResponseEntity<Object> addSongToPlaylist(
            @RequestParam Integer songId,
            @RequestParam Integer playlistId){
        songService.addSongToPlaylist(songId, playlistId);

        return new ResponseEntity<>("{ \"message\": \" Song added to playlist successfully  \" }" , HttpStatus.OK);
    }


    @DeleteMapping("/delete-from-playlist")
    public ResponseEntity<Object> deleteSongFromPlaylist(
            @RequestParam Integer songId,
            @RequestParam Integer playlistId) {
        songService.removeSongFromPlaylist(songId,playlistId);

        return new ResponseEntity<>("{\"message\": \" Song deleted from the playlist successfully  \" }", HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SongSearchResponse>> searchSongs(@RequestParam String query){
        return new ResponseEntity<>(
                songService.searchSong(query), HttpStatus.OK
        );
    }

    @GetMapping("/history")
    public ResponseEntity<List<SongResponseDto>> getSongHistory(){
        return ResponseEntity.ok(songService.getUserHistory());
    }

    @GetMapping("/history/new")
    public ResponseEntity<Object> insertHistory(@RequestParam Integer songId){

        songService.insertHistory(songId);
        String response = String.format("{\"message\": \" Song with id %s added to history.  \" }", songId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
