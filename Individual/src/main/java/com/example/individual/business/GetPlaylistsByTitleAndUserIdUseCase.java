package com.example.individual.business;

import com.example.individual.domain.GetPlaylistsByTitleAndUserIdRequest;
import com.example.individual.domain.GetPlaylistsByTitleAndUserIdResponse;

public interface GetPlaylistsByTitleAndUserIdUseCase {
    GetPlaylistsByTitleAndUserIdResponse getPlaylists(GetPlaylistsByTitleAndUserIdRequest request);
}
