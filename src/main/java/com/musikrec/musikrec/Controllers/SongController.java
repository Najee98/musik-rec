package com.musikrec.musikrec.Controllers;

import com.musikrec.musikrec.Models.Song;
import com.musikrec.musikrec.Services.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/songs")
public class SongController {

    private final SongService songService;

    @GetMapping("/get")
    public ResponseEntity<Song> getSongByName(@RequestParam String title) {

        return ResponseEntity.ok(songService.getSongByTitle(title));

    }

}
