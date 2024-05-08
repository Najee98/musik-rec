package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Models.Recommendation;

public interface RecommendationService {

    Recommendation getRecommendationById (Long id);
}
