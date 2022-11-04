package com.example.individual.business;


import com.example.individual.domain.GetPlaylistSongsRequest;
import com.example.individual.domain.GetPlaylistSongsResponse;

public interface GetPlaylistSongsUseCase {
    GetPlaylistSongsResponse getSongs(GetPlaylistSongsRequest request);
}
