package com.example.individual.business;

import com.example.individual.domain.GetAllPlaylistsRequest;
import com.example.individual.domain.GetAllPlaylistsResponse;

public interface GetAllPlaylistsByUserIdUseCase {
    GetAllPlaylistsResponse getPlaylists(GetAllPlaylistsRequest request);
}
