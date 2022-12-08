package com.example.individual.business.impl;

import com.example.individual.business.GetAllPlaylistsUseCase;
import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.domain.*;
import com.example.individual.repository.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllPlaylistsUseCaseImpl implements GetAllPlaylistsUseCase {
    private PlaylistRepository playlistRepository;

    @Override
    public GetAllPlaylistsResponse getPlaylists() {
        final GetAllPlaylistsResponse response = new GetAllPlaylistsResponse();
        response.setPlaylists(playlistRepository.findAll().stream().map(playlistEntity -> PlaylistConverter.convertToPlaylist(playlistEntity)).toList());

        return response;
}
}
