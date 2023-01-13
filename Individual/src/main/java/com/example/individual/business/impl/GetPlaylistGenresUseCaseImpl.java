package com.example.individual.business.impl;

import com.example.individual.business.GetPlaylistGenresUseCase;
import com.example.individual.business.converter.GenreConverter;
import com.example.individual.business.converter.PlaylistGenreConverter;
import com.example.individual.domain.Genre;
import com.example.individual.domain.GetPlaylistGenresRequest;
import com.example.individual.domain.GetPlaylistGenresResponse;
import com.example.individual.domain.PlaylistGenre;
import com.example.individual.repository.GenreRepository;
import com.example.individual.repository.PlaylistGenresRepository;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.PlaylistGenreEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetPlaylistGenresUseCaseImpl implements GetPlaylistGenresUseCase {
    private PlaylistGenresRepository playlistGenresRepository;
    private PlaylistRepository playlistRepository;
    private GenreRepository genreRepository;
    @Override
    public GetPlaylistGenresResponse getGenres(GetPlaylistGenresRequest request) {
        List<PlaylistGenre> genres;
        if (playlistRepository.existsByPlaylistId(request.getPlaylistId())) {
            genres = playlistGenresRepository.findAllByPlaylist(playlistRepository.findByPlaylistId(request.getPlaylistId())).stream()
                    .map(PlaylistGenreConverter::convertToPlaylistGenre).toList();
            List<Genre> results = genres.stream().map(genre -> GenreConverter.convertToGenre(genreRepository.findByGenreId(genre.getGenre().getId()))).toList();
            return GetPlaylistGenresResponse.builder().genres(results).build();
        } else {
            return GetPlaylistGenresResponse.builder().build();
        }
    }
}
