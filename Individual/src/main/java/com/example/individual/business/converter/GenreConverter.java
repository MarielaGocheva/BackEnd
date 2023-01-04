package com.example.individual.business.converter;

import com.example.individual.domain.Genre;
import com.example.individual.domain.Song;
import com.example.individual.repository.entity.GenreEntity;
import com.example.individual.repository.entity.SongEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class GenreConverter {
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
