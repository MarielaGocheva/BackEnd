package com.example.individual.business.impl;

import com.example.individual.business.GetAllPlaylistsUseCase;
import com.example.individual.domain.*;
import com.example.individual.repository.converter.PlaylistConverter;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.entity.PlaylistEntity;
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
        response.setPlaylists(playlistRepository.findAll());

        return response;
}
}
