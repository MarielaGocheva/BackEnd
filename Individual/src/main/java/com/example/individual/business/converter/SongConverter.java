package com.example.individual.business.converter;

import com.example.individual.domain.Song;
import com.example.individual.repository.entity.SongEntity;

public final class SongConverter {
    private SongConverter(){

    }
    public static Song convertToSong(SongEntity song) {
        return Song.builder()
                .id(song.getId())
                .songUri(song.getSongUri())
                .artist(song.getArtist())
                .title(song.getTitle())
                .duration(song.getDuration())
                .imageUrl(song.getImageUrl())
                .build();
    }

    public static SongEntity convertToSongEntity(Song song) {
        return SongEntity.builder()
                .id(song.getId())
                .songUri(song.getSongUri())
                .artist(song.getArtist())
                .title(song.getTitle())
                .duration(song.getDuration())
                .imageUrl(song.getImageUrl())
                .build();
    }
}
