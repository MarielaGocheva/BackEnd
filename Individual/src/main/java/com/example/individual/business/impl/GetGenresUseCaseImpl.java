package com.example.individual.business.impl;

import com.example.individual.business.GetGenresUseCase;
import com.example.individual.business.converter.GenreConverter;
import com.example.individual.domain.Genre;
import com.example.individual.domain.GetGenresResponse;
import com.example.individual.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetGenresUseCaseImpl implements GetGenresUseCase {
    private GenreRepository genreRepository;
    @Override
    public GetGenresResponse getGenres(){
        List<Genre> genres = genreRepository.findAll().stream().map(GenreConverter::convertToGenre).toList();
        final GetGenresResponse response = new GetGenresResponse();
        response.setGenres(genres);
        return response;
    }

}
