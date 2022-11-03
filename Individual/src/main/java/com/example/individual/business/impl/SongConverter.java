package com.example.individual.business.impl;

import com.example.individual.domain.Song;
import com.example.individual.repository.entity.SongEntity;

final class SongConverter {
    private SongConverter() {

    }
    public static Song convert(SongEntity song) {
        return Song.builder()
                .id(song.getId())
                .songUri(song.getSongUri())
                .artist(song.getArtist())
                .duration(song.getDuration())
                .imageUrl(song.getImageUrl())
                .playlistId(song.getPlaylistId())
                .build();
    }
}
