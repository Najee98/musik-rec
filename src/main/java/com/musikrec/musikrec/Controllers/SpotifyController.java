package com.musikrec.musikrec.Controllers;

import com.musikrec.musikrec.Integration.SpotifyAPI.ApiResponse.SpotifySearchResponse;
import com.musikrec.musikrec.Integration.SpotifyAPI.SpotifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/spotify")
@RequiredArgsConstructor
public class SpotifyController {

    private final SpotifyService spotifyService;

    @GetMapping("/search")
    public ResponseEntity<SpotifySearchResponse> search(@RequestParam String query) {

        return new ResponseEntity<>(spotifyService.searchTracks(query), HttpStatus.OK);
    }

}
