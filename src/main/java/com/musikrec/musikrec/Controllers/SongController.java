package com.musikrec.musikrec.Controllers;

import com.musikrec.musikrec.Models.Song;
import com.musikrec.musikrec.Services.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/songs")
public class SongController {

    private final SongService songService;

    @GetMapping()
    public ResponseEntity<List<Song>> getAllSongs() {
        return ResponseEntity.ok(songService.getAllSongs());
    }

    // path variable: songs/get/1
    // request param: songs/get?id=1
    @GetMapping("/get/{id}")
    public ResponseEntity<Song> getSongByName(@PathVariable Long id) {
        return ResponseEntity.ok(songService.getSong(id));
    }

    //request body: when receiving JSON from front-end
    @PostMapping("/add")
    public void insertSong(@RequestBody Song song) {
        songService.insertSong(song);
    }

    @PutMapping("/update")
    public void updateSong(@RequestBody Song song) {
        songService.updateSong(song);
    }

    @DeleteMapping("/delete")
    public void deleteSong(@RequestParam Long id) {
        songService.deleteSong(id);
    }

}
