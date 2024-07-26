package com.musikrec.musikrec.Integration.SpotifyAPI;

import com.musikrec.musikrec.Integration.SpotifyAPI.ApiResponse.SpotifySearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class SpotifyService {
    private final SpotifyConfig spotifyConfig;
    private final RestTemplate restTemplate;

    @Autowired
    public SpotifyService(SpotifyConfig spotifyConfig, RestTemplateBuilder restTemplateBuilder) {
        this.spotifyConfig = spotifyConfig;
        this.restTemplate = restTemplateBuilder.build();
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
}
