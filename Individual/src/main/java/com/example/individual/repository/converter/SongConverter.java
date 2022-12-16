package com.example.individual.repository.converter;

import com.example.individual.domain.Song;
import com.example.individual.repository.entity.SongEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SongConverter {
    public Song ConvertToSong(SongEntity song) {
        return Song.builder()
                .id(song.getId())
                .songUri(song.getSongUri())
                .artist(song.getArtist())
                .duration(song.getDuration())
                .imageUrl(song.getImageUrl())
                .build();
    }

    public SongEntity ConvertToSongEntity(Song song) {
        return SongEntity.builder()
                .id(song.getId())
                .songUri(song.getSongUri())
                .artist(song.getArtist())
                .duration(song.getDuration())
                .imageUrl(song.getImageUrl())
                .build();
    }
}
