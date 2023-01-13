package com.example.individual.business.converter;

import com.example.individual.domain.Genre;
import com.example.individual.repository.entity.GenreEntity;

public final class GenreConverter {
    private GenreConverter() {
        //Constructor
    }
    public static Genre convertToGenre(GenreEntity genre) {
        return Genre.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }

    public static GenreEntity convertToGenreEntity(Genre genre) {
        return GenreEntity.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}
