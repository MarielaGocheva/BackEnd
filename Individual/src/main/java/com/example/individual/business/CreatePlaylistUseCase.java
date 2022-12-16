package com.example.individual.business;

import com.example.individual.domain.CreatePlaylistRequest;
import com.example.individual.domain.CreatePlaylistResponse;

public interface CreatePlaylistUseCase {
    CreatePlaylistResponse createPlaylist (CreatePlaylistRequest request);
}
