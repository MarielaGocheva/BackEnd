package com.example.individual.business;

import com.example.individual.domain.DeletePlaylistRequest;
import com.example.individual.domain.DeletePlaylistResponse;

public interface DeletePlaylistUseCase {
    DeletePlaylistResponse deletePlaylist(DeletePlaylistRequest request);
}
