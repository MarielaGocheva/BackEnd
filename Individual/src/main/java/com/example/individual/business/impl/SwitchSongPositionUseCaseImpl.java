package com.example.individual.business.impl;

import com.example.individual.business.SwitchSongPositionUseCase;
import com.example.individual.business.exceptions.PlaylistDoesNotExist;
import com.example.individual.business.exceptions.UserAlreadyExists;
import com.example.individual.domain.SwitchSongPositionRequest;
import com.example.individual.domain.SwitchSongPositionResponse;
import com.example.individual.repository.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SwitchSongPositionUseCaseImpl implements SwitchSongPositionUseCase {
    private final PlaylistRepository playlistRepository;
    @Override
    public SwitchSongPositionResponse switchPosition(SwitchSongPositionRequest request) {
        if (!playlistRepository.existsById(request.getPlaylistId())) {
            throw new PlaylistDoesNotExist();
        }
        //Finish with a method from the repo
        return null;
    }
}
