package com.example.individual.business.impl;

import com.example.individual.business.GetPlaylistsByTitleAndUserIdUseCase;
import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.domain.GetPlaylistsByTitleAndUserIdRequest;
import com.example.individual.domain.GetPlaylistsByTitleAndUserIdResponse;
import com.example.individual.domain.Playlist;
import com.example.individual.repository.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetPlaylistsByTitleAndUserIdUseCaseImpl implements GetPlaylistsByTitleAndUserIdUseCase {
    private PlaylistRepository playlistRepository;
    @Override
    public GetPlaylistsByTitleAndUserIdResponse getPlaylists(GetPlaylistsByTitleAndUserIdRequest request) {
        Playlist result;

        if (request.getUserId() > 0) {
            result = PlaylistConverter.convertToPlaylist(playlistRepository.findByTitleAndUserId(request.getTitle(), request.getUserId()));
        } else {
            result = null;
        }

        final GetPlaylistsByTitleAndUserIdResponse response = new GetPlaylistsByTitleAndUserIdResponse();
        response.setPlaylist(result);
        return response;
    }
}
