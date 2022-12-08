package com.example.individual.business.converter;

import com.example.individual.business.converter.SongConverter;
import com.example.individual.domain.Playlist;
import com.example.individual.domain.Song;
import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.SongEntity;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public final class PlaylistConverter {

//    private SongConverter songConverter;
    public static Playlist convertToPlaylist(PlaylistEntity playlist) {
        List<Song> convertedSongs = new ArrayList<>();
        for (SongEntity song : playlist.getSongs())
        {
            convertedSongs.add(SongConverter.convertToSong(song));
        }
        return Playlist.builder()
                .id(playlist.getPlaylistId())
                .userId(playlist.getUserId())
                .title(playlist.getTitle())
                .duration(playlist.getDuration())
                .songs(convertedSongs)
                .build();
    }

    public static PlaylistEntity convertToPlaylistEntity(Playlist playlist) {
        List<SongEntity> convertedSongs = new ArrayList<>();
        for (Song song : playlist.getSongs()) {
            convertedSongs.add(SongConverter.convertToSongEntity(song));
        }
        return PlaylistEntity.builder()
                .playlistId(playlist.getId())
                .userId(playlist.getUserId())
                .title(playlist.getTitle())
                .duration(playlist.getDuration())
//                .songs(convertedSongs)
                .build();
    }
}
