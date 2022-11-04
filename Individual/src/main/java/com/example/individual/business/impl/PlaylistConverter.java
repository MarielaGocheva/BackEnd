package com.example.individual.business.impl;

import com.example.individual.domain.Playlist;
import com.example.individual.domain.Song;
import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.SongEntity;

import java.util.ArrayList;
import java.util.List;

public class PlaylistConverter {
    private PlaylistConverter() {
    }

    public static Playlist convert(PlaylistEntity playlist) {
        return Playlist.builder()
                .id(playlist.getId())
                .userId(playlist.getUserId())
                .duration(playlist.getDuration())
                .songs(convert(playlist.getSongs()))
                .build();
    }

    public static List<Song> convert (List<SongEntity> songs) {
        List<Song> converted = new ArrayList<>();
        for (SongEntity song : songs)
        {
            converted.add(Song.builder()
                    .id(song.getId())
                    .songUri(song.getSongUri())
                    .artist(song.getArtist())
                    .duration(song.getDuration())
                    .imageUrl(song.getImageUrl())
                    .playlistId(song.getPlaylistId())
                    .build());
        }
        return converted;
    }
}
