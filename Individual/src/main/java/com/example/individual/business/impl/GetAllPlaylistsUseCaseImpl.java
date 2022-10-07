package com.example.individual.business.impl;

import com.example.individual.business.GetAllPlaylistsUseCase;
import com.example.individual.domain.*;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.entity.PlaylistEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllPlaylistsUseCaseImpl implements GetAllPlaylistsUseCase {
    private PlaylistRepository playlistRepository;

    @Override
    public GetAllPlaylistsResponse getPlaylists() {
        List<PlaylistEntity> results = playlistRepository.findAll();

        final GetAllPlaylistsResponse response = new GetAllPlaylistsResponse();
        List<Playlist> playlists = results
                .stream()
                .map(PlaylistConverter::convert)
                .toList();
        response.setPlaylists(playlists);

        return response;
}
}
