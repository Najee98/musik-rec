package com.musikrec.musikrec.Services;


import com.musikrec.musikrec.Models.Recommendation;
import com.musikrec.musikrec.Repositories.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImp implements RecommendationService{

    private final RecommendationRepository recommendationRepository ;

    @Override

    public Recommendation getRecommendationById (Long id)
    {
        return recommendationRepository.findById(id).orElse(null);
    }
}
