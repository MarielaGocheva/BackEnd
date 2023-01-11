package com.example.individual.business;

import com.example.individual.domain.SetRecentlyPlayedRequest;
import com.example.individual.domain.SetRecentlyPlayedResponse;

public interface SetRecentlyPlayedUseCase {
    SetRecentlyPlayedResponse setRecentlyPlayed(SetRecentlyPlayedRequest request);
}
