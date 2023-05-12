package com.example.individual.business;

import com.example.individual.domain.GetRecommendationsRequest;
import com.example.individual.domain.GetRecommendationsResponse;

public interface GetRecommendationsUseCase {
    GetRecommendationsResponse getRecommendations(GetRecommendationsRequest request);
}
