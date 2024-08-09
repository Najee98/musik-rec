package com.musikrec.musikrec.Controllers;

import com.musikrec.musikrec.Dto.Responses.SongResponseDto;
import com.musikrec.musikrec.Integration.MusicRecommender.MusicRecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recommendations")
public class RecommendationsController {

    private final MusicRecommendationService recommendationsService;

    @GetMapping
    public ResponseEntity<List<SongResponseDto>> getRecommendations(@RequestParam Integer userId) {

        return new ResponseEntity<>(recommendationsService.getRecommendationsForUser(userId), HttpStatus.OK);

    }

}
