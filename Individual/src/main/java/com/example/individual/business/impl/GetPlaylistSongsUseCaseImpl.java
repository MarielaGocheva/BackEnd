package com.example.individual.business.impl;

import com.example.individual.business.GetPlaylistSongsUseCase;
import com.example.individual.business.converter.SongConverter;
import com.example.individual.domain.*;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.SongRepository;
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
        if (request.getPlaylistId() >= 1) {
            PlaylistEntity pl = playlistRepository.findByPlaylistId(request.getPlaylistId());
            songs = pl.getSongs().stream().map(song -> SongConverter.convertToSong(song)).toList();
        } else {
            songs = null;
        }

        final GetPlaylistSongsResponse response = new GetPlaylistSongsResponse();
        response.setSongs(songs);

        return response;
    }
}
