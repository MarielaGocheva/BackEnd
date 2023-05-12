package com.example.individual.business.impl;

import com.example.individual.domain.Playlist;
import com.example.individual.domain.Song;
import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.SongEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlaylistConverterTest {
    @Test
    void shouldConvertAllPlaylistEntitiesToDomain() {
        List<SongEntity> songs = new ArrayList<>();
        songs.add(SongEntity.builder().artist("Adele").build());
        songs.add(SongEntity.builder().artist("Alter Bridge").build());
        PlaylistEntity playlistTobeConverted = PlaylistEntity.builder()
                .playlistId(1L)
                .userId(2L)
                .songs(songs)
                .duration(44D)
                .build();

        Long actualId = PlaylistConverter.convertToPlaylist(playlistTobeConverted).getId();
        Long actualUserId = PlaylistConverter.convertToPlaylist(playlistTobeConverted).getUserId();
        String actualPlaylistSong = PlaylistConverter.convertToPlaylist(playlistTobeConverted).getSongs().get(0).getArtist();
        double actualDuration = PlaylistConverter.convertToPlaylist(playlistTobeConverted).getDuration();

        List<Song> songs2 = new ArrayList<>();
        songs2.add(Song.builder().artist("Adele").build());
        songs2.add(Song.builder().artist("Alter Bridge").build());
        Playlist expected = Playlist.builder()
                .id(1L)
                .userId(2L)
                .songs(songs2)
                .duration(44D)
                .build();

        assertEquals(expected.getId(), actualId);
        assertEquals(expected.getUserId(), actualUserId);
        assertEquals(expected.getSongs().get(0).getArtist(), actualPlaylistSong);
        assertEquals(expected.getDuration(), actualDuration);
    }

    @Test
    void shouldConvertAllPlaylistDomainObjectsToEntities() {
        List<Song> songs = new ArrayList<>();
        songs.add(Song.builder().artist("Adele").build());
        songs.add(Song.builder().artist("Alter Bridge").build());
        Playlist playlistTobeConverted = Playlist.builder()
                .id(1L)
                .userId(2L)
                .songs(songs)
                .duration(44D)
                .build();

        Long actualId = PlaylistConverter.convertToPlaylistEntity(playlistTobeConverted).getPlaylistId();
        Long actualUserId = PlaylistConverter.convertToPlaylistEntity(playlistTobeConverted).getUserId();
        String actualPlaylistSong = PlaylistConverter.convertToPlaylistEntity(playlistTobeConverted).getSongs().get(0).getArtist();
        double actualDuration = PlaylistConverter.convertToPlaylistEntity(playlistTobeConverted).getDuration();

        List<SongEntity> songs2 = new ArrayList<>();
        songs2.add(SongEntity.builder().artist("Adele").build());
        songs2.add(SongEntity.builder().artist("Alter Bridge").build());
        PlaylistEntity expected = PlaylistEntity.builder()
                .playlistId(1L)
                .userId(2L)
                .songs(songs2)
                .duration(44D)
                .build();

        assertEquals(expected.getPlaylistId(), actualId);
        assertEquals(expected.getUserId(), actualUserId);
        assertEquals(expected.getSongs().get(0).getArtist(), actualPlaylistSong);
        assertEquals(expected.getDuration(), actualDuration);
    }
}