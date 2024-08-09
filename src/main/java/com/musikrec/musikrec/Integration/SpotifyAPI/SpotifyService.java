package com.musikrec.musikrec.Integration.SpotifyAPI;

import com.musikrec.musikrec.Integration.SpotifyAPI.ApiResponse.SpotifySearchResponse;
import com.musikrec.musikrec.Models.Album;
import com.musikrec.musikrec.Models.Artist;
import com.musikrec.musikrec.Models.Song;
import com.musikrec.musikrec.Repositories.AlbumRepository;
import com.musikrec.musikrec.Repositories.ArtistRepository;
import com.musikrec.musikrec.Repositories.SongRepository;
import com.musikrec.musikrec.Services.ArtistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SpotifyService {
    private final SpotifyConfig spotifyConfig;
    private final RestTemplate restTemplate;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final ArtistServiceImpl artistServiceImpl;
    private final SongRepository songRepository;

    @Autowired
    public SpotifyService(SpotifyConfig spotifyConfig, RestTemplateBuilder restTemplateBuilder, ArtistRepository artistRepository, AlbumRepository albumRepository, ArtistServiceImpl artistServiceImpl, SongRepository songRepository) {
        this.spotifyConfig = spotifyConfig;
        this.restTemplate = restTemplateBuilder.build();
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.artistServiceImpl = artistServiceImpl;
        this.songRepository = songRepository;
    }

    // Method to get the access token
    public String getAccessToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(spotifyConfig.getClientId(), spotifyConfig.getClientSecret());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(spotifyConfig.getTokenUrl(), HttpMethod.POST, entity, Map.class);

        return (String) response.getBody().get("access_token");
    }

    public SpotifySearchResponse searchTracks(String query) {
        String accessToken = getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String searchUrl = "https://api.spotify.com/v1/search?q=" + query + "&type=track";

        ResponseEntity<SpotifySearchResponse> response = restTemplate.exchange(searchUrl, HttpMethod.GET, entity, SpotifySearchResponse.class);

        return response.getBody();
    }


    // New method to fetch and store 200 songs from Spotify
    public void fetchAndStoreSongs(int totalSongs) {
        String accessToken = getAccessToken();
        int limit = 50; // Spotify API allows max 50 items per request
        int offset = 0;

        while (totalSongs > 0) {
            int fetchCount = Math.min(totalSongs, limit);

            List<Song> songs = fetchSongsFromSpotify(accessToken, fetchCount, offset);
            songRepository.saveAll(songs); // Persist songs to database

            totalSongs -= fetchCount;
            offset += fetchCount;
        }
    }

    // Helper method to fetch songs from Spotify API
    private List<Song> fetchSongsFromSpotify(String accessToken, int limit, int offset) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

//        String searchUrl = "https://api.spotify.com/v1/tracks?limit=" + limit + "&offset=" + offset;

        String searchUrl = "https://api.spotify.com/v1/search?q=your_query&type=track&limit=" + limit + "&offset=" + offset;

        ResponseEntity<SpotifySearchResponse> response = restTemplate.exchange(searchUrl, HttpMethod.GET, entity, SpotifySearchResponse.class);

        return mapToSongEntities(response.getBody());
    }

    // Helper method to map Spotify response to Song entities
    private List<Song> mapToSongEntities(SpotifySearchResponse spotifySearchResponse) {
        return spotifySearchResponse.getTracks().getItems().stream().map(track -> {
            Song song = new Song();

            song.setTitle(track.getName());
            Artist artist = new Artist();
            artist.setName(track.getArtists().get(0).getName());
            artist.setSongs(null);
            artist.setArtistAlbums(null);
            artistRepository.save(artist);

            song.setArtist(artist);

            Album album = new Album();
            album.setTitle(track.getAlbum().getName());
            album.setImageUrl(track.getAlbum().getImages().get(0).getUrl());
            album.setSongs(null);
            album.setArtist(artist);

            albumRepository.save(album);

            song.setAlbum(album);
            song.setReleaseYear(null);
            song.setImageUrl(track.getAlbum().getImages().get(0).getUrl());
            song.setPreviewUrl(track.getPreview_url());
            return song;
        }).collect(Collectors.toList());
    }
}
