package com.example.individual.business;

import com.example.individual.domain.GetPlaylistGenresRequest;
import com.example.individual.domain.GetPlaylistGenresResponse;

public interface GetPlaylistGenresUseCase {
    GetPlaylistGenresResponse getGenres(GetPlaylistGenresRequest request);
}
