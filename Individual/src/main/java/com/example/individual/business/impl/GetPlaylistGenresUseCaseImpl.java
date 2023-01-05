package com.example.individual.business.impl;

import com.example.individual.business.GetPlaylistGenresUseCase;
import com.example.individual.business.converter.GenreConverter;
import com.example.individual.domain.Genre;
import com.example.individual.domain.GetPlaylistGenresRequest;
import com.example.individual.domain.GetPlaylistGenresResponse;
import com.example.individual.repository.PlaylistGenresRepository;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.entity.PlaylistEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetPlaylistGenresUseCaseImpl implements GetPlaylistGenresUseCase {
    private PlaylistGenresRepository playlistGenresRepository;
    private PlaylistRepository playlistRepository;
    @Override
    public GetPlaylistGenresResponse getGenres(GetPlaylistGenresRequest request) {
        List<Genre> genres;
        if (playlistRepository.existsById(request.getPlaylistId())) {
            PlaylistEntity pl = playlistRepository.findByPlaylistId(request.getPlaylistId());
            genres = pl.getGenres().stream().map(GenreConverter::convertToGenre).toList();
        } else {
            genres = null;
        }
        GetPlaylistGenresResponse response = GetPlaylistGenresResponse.builder().build();
        response.setGenres(genres);
        return response;
    }
}
