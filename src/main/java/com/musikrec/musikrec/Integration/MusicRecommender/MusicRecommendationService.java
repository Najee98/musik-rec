package com.musikrec.musikrec.Integration.MusicRecommender;

import com.musikrec.musikrec.Dto.Responses.SongResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MusicRecommendationService {

    private final RestTemplate restTemplate;

    @Autowired
    public MusicRecommendationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<SongResponseDto> getRecommendationsForUser(int userId) {
        // The URL of the Django API endpoint
        String djangoApiUrl = "http://localhost:8000/songs";

        // Create the request body with the user ID
        Map<String, Integer> requestBody = new HashMap<>();
        requestBody.put("user_id", userId);

        // Set headers if needed, such as content type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the HttpEntity with the headers and body
        HttpEntity<Map<String, Integer>> entity = new HttpEntity<>(requestBody, headers);

        try {
            // Make the POST request to the Django API
            ResponseEntity<List<SongResponseDto>> response = restTemplate.exchange(
                    djangoApiUrl,
                    HttpMethod.POST,
                    entity,
                    new ParameterizedTypeReference<List<SongResponseDto>>() {}
            );

            // Return the response body (which is a list of SongResponseDto)
            return response.getBody();
        } catch (HttpClientErrorException e) {
            // Handle the error case (e.g., 400 Bad Request)
            System.err.println("Request failed: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
