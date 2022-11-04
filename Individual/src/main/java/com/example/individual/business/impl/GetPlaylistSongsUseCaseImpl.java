package com.example.individual.business.impl;

import com.example.individual.business.GetPlaylistSongsUseCase;
import com.example.individual.domain.*;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.SongEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetPlaylistSongsUseCaseImpl implements GetPlaylistSongsUseCase {
    private PlaylistRepository playlistRepository;

    @Override
    public GetPlaylistSongsResponse getSongs(GetPlaylistSongsRequest request) {
        List<SongEntity> songs;
        if (request.getPlaylistId() >= 1) {
            songs = playlistRepository.getSongs(request.getPlaylistId());
        } else {
            songs = null;
        }

        final GetPlaylistSongsResponse response = new GetPlaylistSongsResponse();
        List<Song> plSongs = songs
                .stream()
                .map(SongConverter::convert)
                .toList();
        response.setSongs(plSongs);

        return response;
    }
}
