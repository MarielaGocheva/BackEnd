package com.example.individual.business.impl;

import com.example.individual.domain.Playlist;
import com.example.individual.domain.Song;
import com.example.individual.repository.converter.PlaylistConverter;
import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.SongEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlaylistConverterTest {
    @InjectMocks
    private PlaylistConverter playlistConverter;
    @Test
    void shouldConvertAllPlaylistFieldsToDomain() {
        List<SongEntity> songs = new ArrayList<>();
        PlaylistEntity playlistTobeConverted = PlaylistEntity.builder()
                .id(1L)
                .userId(2L)
                .songs(songs)
                .duration(44D)
                .build();

        Playlist actual = playlistConverter.convertToPlaylist(playlistTobeConverted);

        List<Song> songs2 = new ArrayList<>();
        Playlist expected = Playlist.builder()
                .id(1L)
                .userId(2L)
                .songs(songs2)
                .duration(44D)
                .build();

        assertEquals(expected, actual);
    }
}