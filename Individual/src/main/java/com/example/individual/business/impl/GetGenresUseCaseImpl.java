package com.example.individual.business.impl;

import com.example.individual.business.GetGenresUseCase;
import com.example.individual.business.converter.GenreConverter;
import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.business.converter.SongConverter;
import com.example.individual.domain.*;
import com.example.individual.repository.GenreRepository;
import com.example.individual.repository.PlaylistGenresRepository;
import com.example.individual.repository.entity.PlaylistEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetGenresUseCaseImpl implements GetGenresUseCase {
    private GenreRepository genreRepository;
    @Override
    public GetGenresResponse getGenres(){
        List<Genre> genres = genreRepository.findAll().stream().map(genre -> GenreConverter.convertToGenre(genre)).toList();
        final GetGenresResponse response = new GetGenresResponse();
        response.setGenres(genres);

        return response;
//        final GetGenresResponse response = new GetGenresResponse();
//        response.
//
//        return response;
    }

}
