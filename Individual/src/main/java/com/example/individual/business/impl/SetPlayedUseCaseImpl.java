package com.example.individual.business.impl;

import com.example.individual.business.SetPlayedUseCase;
import com.example.individual.business.exceptions.PlaylistDoesNotExist;
import com.example.individual.domain.SetPlayedRequest;
import com.example.individual.repository.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SetPlayedUseCaseImpl implements SetPlayedUseCase {
    private PlaylistRepository playlistRepository;
    @Override
    public void setPlayed(SetPlayedRequest request) {
        if(playlistRepository.existsById(request.getPlaylistId()) && request.getPlaylistId() != null){
            playlistRepository.updatePlays(request.getPlaylistId());
        }
        else {
            throw new PlaylistDoesNotExist();
        }
    }
}
