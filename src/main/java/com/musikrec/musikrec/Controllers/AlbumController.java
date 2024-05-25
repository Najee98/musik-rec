package com.musikrec.musikrec.Controllers;

import com.musikrec.musikrec.Dto.Responses.AlbumDetailsDto;
import com.musikrec.musikrec.Dto.Responses.AlbumDto;
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
    public ResponseEntity<List<AlbumDto>> getAllAlbums() {
        return ResponseEntity.ok(albumService.getAllAlbums());
    }


    @GetMapping("/get/{albumId}")
    public ResponseEntity<AlbumDetailsDto> getAlbum(@PathVariable Long albumId) {
        return ResponseEntity.ok(albumService.getAlbum(albumId));
    }




}
