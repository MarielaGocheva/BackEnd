package com.example.individual.business;

import com.example.individual.domain.AddSongRequest;
import com.example.individual.domain.AddSongResponse;


public interface AddSongUseCase {
    AddSongResponse addSong(AddSongRequest request);
}
