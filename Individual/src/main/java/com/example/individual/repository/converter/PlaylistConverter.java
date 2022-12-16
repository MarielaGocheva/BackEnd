package com.example.individual.repository.converter;

import com.example.individual.domain.Playlist;
import com.example.individual.domain.Song;
import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.SongEntity;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PlaylistConverter {

    private SongConverter songConverter;
    public Playlist ConvertToPlaylist(PlaylistEntity playlist) {
        List<Song> convertedSongs = new ArrayList<>();
        for (SongEntity song : playlist.getSongs())
        {
            convertedSongs.add(songConverter.ConvertToSong(song));
        }
        return Playlist.builder()
                .id(playlist.getId())
                .userId(playlist.getUserId())
                .duration(playlist.getDuration())
                .songs(convertedSongs)
                .build();
    }

    public PlaylistEntity ConvertToPlaylistEntity(Playlist playlist) {
        List<SongEntity> convertedSongs = new ArrayList<>();
        for (Song song : playlist.getSongs()) {
            convertedSongs.add(songConverter.ConvertToSongEntity(song));
        }
        return PlaylistEntity.builder()
                .id(playlist.getId())
                .userId(playlist.getUserId())
                .duration(playlist.getDuration())
                .songs(convertedSongs)
                .build();
    }
}
