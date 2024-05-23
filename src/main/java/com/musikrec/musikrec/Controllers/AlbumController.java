package com.musikrec.musikrec.Controllers;

import com.musikrec.musikrec.Models.Album;
import com.musikrec.musikrec.Services.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/albums")
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping()
    public ResponseEntity<List<Album>> getAllAlbums(@RequestParam String artist) {
        return ResponseEntity.ok(albumService.getAllAlbums(artist));
    }



}
