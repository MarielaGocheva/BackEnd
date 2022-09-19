package com.example.individual.business.impl;

import com.example.individual.domain.Playlist;
import com.example.individual.repository.entity.PlaylistEntity;

final class PlaylistConverter {
    private PlaylistConverter() {
    }

    public static Playlist convert(PlaylistEntity playlist) {
        return Playlist.builder()
                .id(playlist.getId())
                .userId(playlist.getUserId())
                .duration(playlist.getDuration())
                .build();
    }
}
