package com.musikrec.musikrec.Controllers;

import com.musikrec.musikrec.Models.Album;
import com.musikrec.musikrec.Services.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/albums")
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping()
    public ResponseEntity<List<Album>> getAllAlbumsForArtist(@RequestParam String artist) {
        return ResponseEntity.ok(albumService.getAllAlbumsForArtist(artist));
    }


    @GetMapping("/get/{title}")
    public ResponseEntity<Album> getAlbumDetails(@PathVariable String title) {
        return ResponseEntity.ok(albumService.getAlbumDetails(title));
    }




}
