package com.example.individual.business.converter;

import com.example.individual.domain.Genre;
import com.example.individual.domain.PlaylistGenre;
import com.example.individual.repository.entity.GenreEntity;
import com.example.individual.repository.entity.PlaylistGenreEntity;

public final class PlaylistGenreConverter {
    private PlaylistGenreConverter(){
        //Constructor
    }
    public static PlaylistGenre convertToPlaylistGenre(PlaylistGenreEntity genre) {
        return PlaylistGenre.builder()
                .playlist(PlaylistConverter.convertToPlaylist(genre.getPlaylist()))
                .genre(GenreConverter.convertToGenre(genre.getGenre()))
                .build();
    }

    public static PlaylistGenreEntity convertToPlaylistGenreEntity(PlaylistGenre genre) {
        return PlaylistGenreEntity.builder()
                .playlist(PlaylistConverter.convertToPlaylistEntity(genre.getPlaylist()))
                .genre(GenreConverter.convertToGenreEntity(genre.getGenre()))
                .build();
    }

}
