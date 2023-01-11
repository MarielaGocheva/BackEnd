package com.example.individual.business;

import com.example.individual.domain.GetRecentlyPlayedRequest;
import com.example.individual.domain.GetRecentlyPlayedResponse;

public interface GetRecentlyPlayedUseCase {
    GetRecentlyPlayedResponse getRecentlyPlayed(GetRecentlyPlayedRequest request);
}
