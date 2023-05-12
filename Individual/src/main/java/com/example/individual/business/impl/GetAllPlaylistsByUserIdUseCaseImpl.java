package com.example.individual.business.impl;

import com.example.individual.business.GetAllPlaylistsByUserIdUseCase;
import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.business.exceptions.UserNotFoundException;
import com.example.individual.domain.GetAllPlaylistsByUserIdRequest;
import com.example.individual.domain.GetAllPlaylistsByUserIdResponse;
import com.example.individual.domain.Playlist;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllPlaylistsByUserIdUseCaseImpl implements GetAllPlaylistsByUserIdUseCase {
    private PlaylistRepository playlistRepository;
    private UserRepository userRepository;

    @Override
    public GetAllPlaylistsByUserIdResponse getPlaylists(final GetAllPlaylistsByUserIdRequest request) {
        List<Playlist> results;
        if (request.getUserId() != null && userRepository.existsById(request.getUserId())) {
            results = playlistRepository.findAllByUserId(request.getUserId()).stream().map(PlaylistConverter::convertToPlaylist).toList();
        } else {
            throw new UserNotFoundException();
        }
        final GetAllPlaylistsByUserIdResponse response = new GetAllPlaylistsByUserIdResponse();
        response.setPlaylists(results);
        return response;
    }
}
