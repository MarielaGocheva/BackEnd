package com.example.individual.business.impl;

import com.example.individual.business.SetSongPlayedUseCase;
import com.example.individual.business.exceptions.SongDoesNotExist;
import com.example.individual.domain.SetSongPlayedRequest;
import com.example.individual.repository.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SetSongPlayedUseCaseImpl implements SetSongPlayedUseCase {
    private SongRepository songRepository;

    @Override
    public void setSongPlayed(SetSongPlayedRequest request) {
        if(songRepository.existsBySongUri(request.getSongUri())){
            songRepository.updatePlays(request.getSongUri());
        }
        else {
            throw new SongDoesNotExist();
        }
    }
}
