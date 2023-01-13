package com.example.individual.business.impl;

import com.example.individual.business.GetMostPlayedPlaylistsUseCase;
import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.domain.GetMostPlayedPlaylistsResponse;
import com.example.individual.repository.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetMostPlayedPlaylistsUseCaseImpl implements GetMostPlayedPlaylistsUseCase {
    private PlaylistRepository playlistRepository;
    @Override
    public GetMostPlayedPlaylistsResponse getMostPlayed() {
        return GetMostPlayedPlaylistsResponse.builder().mostPlayed(
                playlistRepository.findTop20ByOrderByPlaysDesc().stream().map(PlaylistConverter::convertToPlaylist).toList()).build();
    }
}
