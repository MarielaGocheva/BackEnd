package com.example.individual.business.converter;

import com.example.individual.domain.Song;
import com.example.individual.repository.entity.SongEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class SongConverter {
    public static Song convertToSong(SongEntity song) {
        return Song.builder()
                .id(song.getId())
                .songUri(song.getSongUri())
                .artist(song.getArtist())
                .duration(song.getDuration())
                .imageUrl(song.getImageUrl())
                .build();
    }

    public static SongEntity convertToSongEntity(Song song) {
        return SongEntity.builder()
                .id(song.getId())
                .songUri(song.getSongUri())
                .artist(song.getArtist())
                .duration(song.getDuration())
                .imageUrl(song.getImageUrl())
                .build();
    }
}
