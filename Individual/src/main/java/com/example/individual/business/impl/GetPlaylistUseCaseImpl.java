package com.example.individual.business.impl;

import com.example.individual.business.GetPlaylistUseCase;
import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.business.exceptions.PlaylistDoesNotExist;
import com.example.individual.domain.ArtistUpdate;
import com.example.individual.domain.Playlist;
import com.example.individual.repository.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetPlaylistUseCaseImpl implements GetPlaylistUseCase {
    private PlaylistRepository playlistRepository;
    @Override
    public Playlist getPlaylist(Long playlistId) {
        if(playlistRepository.existsById(playlistId)){
            return PlaylistConverter.convertToPlaylist(playlistRepository.findByPlaylistId(playlistId));
        }
        else {
            throw new PlaylistDoesNotExist();
        }
    }
}
