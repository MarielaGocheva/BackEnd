package com.example.individual.business.impl;

import com.example.individual.business.GetAllPlaylistsByUserIdUseCase;
import com.example.individual.domain.GetAllPlaylistsByUserIdRequest;
import com.example.individual.domain.GetAllPlaylistsByUserIdResponse;
import com.example.individual.domain.Playlist;
import com.example.individual.repository.converter.PlaylistConverter;
import com.example.individual.repository.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllPlaylistsByUserIdUseCaseImpl implements GetAllPlaylistsByUserIdUseCase {
    private PlaylistRepository playlistRepository;

    @Override
    public GetAllPlaylistsByUserIdResponse getPlaylists(final GetAllPlaylistsByUserIdRequest request) {
        List<Playlist> results;
        if (request.getUserId() >= 1) {
            results = playlistRepository.findAllByUserId(request.getUserId());
        } else {
            results = null;
        }

        final GetAllPlaylistsByUserIdResponse response = new GetAllPlaylistsByUserIdResponse();
        response.setPlaylists(results);

        return response;
    }
}
