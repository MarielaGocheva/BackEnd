package com.example.individual.business.impl;

import com.example.individual.business.GetPlaylistsByTitleAndUserIdUseCase;
import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.business.exceptions.PlaylistDoesNotExist;
import com.example.individual.business.exceptions.UserNotFoundException;
import com.example.individual.domain.GetPlaylistsByTitleAndUserIdRequest;
import com.example.individual.domain.GetPlaylistsByTitleAndUserIdResponse;
import com.example.individual.domain.Playlist;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetPlaylistsByTitleAndUserIdUseCaseImpl implements GetPlaylistsByTitleAndUserIdUseCase {
    private UserRepository userRepository;
    private PlaylistRepository playlistRepository;
    @Override
    public GetPlaylistsByTitleAndUserIdResponse getPlaylists(GetPlaylistsByTitleAndUserIdRequest request) {
        Playlist result;

        if (userRepository.existsById(request.getUserId())) {
            if(!playlistRepository.existsByTitleAndUserId(request.getTitle(), request.getUserId())){
                throw new PlaylistDoesNotExist();
            }
            else {
                result = PlaylistConverter.convertToPlaylist(playlistRepository.findByTitleAndUserId(request.getTitle(), request.getUserId()));
            }

        } else {
            throw new UserNotFoundException();
        }

        final GetPlaylistsByTitleAndUserIdResponse response = new GetPlaylistsByTitleAndUserIdResponse();
        response.setPlaylist(result);
        return response;
    }
}
