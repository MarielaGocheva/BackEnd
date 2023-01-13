package com.example.individual.business.impl;

import com.example.individual.business.GetPlaylistSongsUseCase;
import com.example.individual.business.converter.SongConverter;
import com.example.individual.business.exceptions.PlaylistDoesNotExist;
import com.example.individual.domain.GetPlaylistSongsRequest;
import com.example.individual.domain.GetPlaylistSongsResponse;
import com.example.individual.domain.Song;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.entity.PlaylistEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetPlaylistSongsUseCaseImpl implements GetPlaylistSongsUseCase {
    private PlaylistRepository playlistRepository;

    @Override
    public GetPlaylistSongsResponse getSongs(GetPlaylistSongsRequest request) {
        List<Song> songs;
        if (playlistRepository.existsById(request.getPlaylistId())) {
            PlaylistEntity pl = playlistRepository.findByPlaylistId(request.getPlaylistId());
            songs = pl.getSongs().stream().map(SongConverter::convertToSong).toList();
        } else {
            throw new PlaylistDoesNotExist();
        }
        return GetPlaylistSongsResponse.builder().songs(songs).build();
    }
}
