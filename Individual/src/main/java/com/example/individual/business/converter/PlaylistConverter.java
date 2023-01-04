package com.example.individual.business.converter;

import com.example.individual.domain.Genre;
import com.example.individual.domain.Playlist;
import com.example.individual.domain.Song;
import com.example.individual.repository.entity.GenreEntity;
import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.SongEntity;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public final class PlaylistConverter {
    public static Playlist convertToPlaylist(PlaylistEntity playlist) {
        List<Song> convertedSongs = new ArrayList<>();
        for (SongEntity song : playlist.getSongs())
        {
            convertedSongs.add(SongConverter.convertToSong(song));
        }
        List<Genre> convertedGenres = new ArrayList<>();
//        for ( GenreEntity genre : playlist.getGenres())
//        {
//            convertedGenres.add(GenreConverter.convertToGenre(genre));
//        }
        return Playlist.builder()
                .id(playlist.getPlaylistId())
                .userId(playlist.getUserId())
                .title(playlist.getTitle())
                .duration(playlist.getDuration())
                .imageUrl(playlist.getImageUrl())
                .songs(convertedSongs)
//                .genres(convertedGenres)
                .build();
    }

    public static PlaylistEntity convertToPlaylistEntity(Playlist playlist) {
        List<SongEntity> convertedSongs = new ArrayList<>();
        for (Song song : playlist.getSongs()) {
            convertedSongs.add(SongConverter.convertToSongEntity(song));
        }
        List<GenreEntity> convertedGenres = new ArrayList<>();
//        for (Genre genre : playlist.getGenres())
//        {
//            convertedGenres.add(GenreConverter.convertToGenreEntity(genre));
//        }
        return PlaylistEntity.builder()
                .playlistId(playlist.getId())
                .userId(playlist.getUserId())
                .title(playlist.getTitle())
                .duration(playlist.getDuration())
                .imageUrl(playlist.getImageUrl())
                .songs(convertedSongs)
//                .genres(convertedGenres)
                .build();
    }
}
