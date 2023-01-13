package com.example.individual.business.impl;

import com.example.individual.business.converter.GenreConverter;
import com.example.individual.domain.Genre;
import com.example.individual.domain.GetGenresResponse;
import com.example.individual.repository.GenreRepository;
import com.example.individual.repository.entity.GenreEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetGenresUseCaseImplTest {
    @Mock
    private GenreRepository genreRepositoryMock;
    @InjectMocks
    private GetGenresUseCaseImpl getGenresUseCase;

    @Test
    void getGenres_shouldReturnAllGenres(){
        List<GenreEntity> genres = new ArrayList<>();
        genres.add(GenreEntity.builder().id(1L).name("Pop").build());
        genres.add(GenreEntity.builder().id(2L).name("Rock").build());
        genres.add(GenreEntity.builder().id(3L).name("Metal").build());
        List<Genre> convertedGenres = genres.stream().map(GenreConverter::convertToGenre).toList();
        when(genreRepositoryMock.findAll()).thenReturn(genres);
        GetGenresResponse actual = getGenresUseCase.getGenres();
        GetGenresResponse expected = GetGenresResponse.builder().genres(convertedGenres).build();
        assertEquals(expected.getGenres().size(), actual.getGenres().size());
        verify(genreRepositoryMock).findAll();
    }
}