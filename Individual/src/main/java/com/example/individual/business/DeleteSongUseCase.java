package com.example.individual.business;

import com.example.individual.domain.DeleteSongRequest;
import com.example.individual.domain.DeleteSongResponse;

public interface DeleteSongUseCase {
    DeleteSongResponse deleteSong(DeleteSongRequest request);
}
