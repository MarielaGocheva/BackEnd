package com.example.individual.business;

import com.example.individual.domain.GetAllPlaylistsByUserIdRequest;
import com.example.individual.domain.GetAllPlaylistsByUserIdResponse;

public interface GetAllPlaylistsByUserIdUseCase {
    GetAllPlaylistsByUserIdResponse getPlaylists(GetAllPlaylistsByUserIdRequest request);
}
