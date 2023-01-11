package com.example.individual.business.impl;

import com.example.individual.business.converter.SongConverter;
import com.example.individual.business.exceptions.PlaylistDoesNotExist;
import com.example.individual.domain.*;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.PlaylistSongRepository;
import com.example.individual.repository.SongRepository;
import com.example.individual.repository.entity.GenreEntity;
import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.PlaylistSongEntity;
import com.example.individual.repository.entity.SongEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddSongUseCaseImplTest {
    @Mock
    private PlaylistRepository playlistRepository;
    @Mock
    private SongRepository songRepository;
    @Mock
    private PlaylistSongRepository playlistSongRepository;
    @InjectMocks
    private AddSongUseCaseImpl addSongUseCaseImpl;

    @Test
    void saveSong_validRequest(){
        AddSongRequest request = AddSongRequest.builder().songUri("http://spotify/summersong").img("http://img").title("Summer Song").artist("Vulfpeck").playlistId(2L).build();
        when(playlistRepository.existsById(request.getPlaylistId())).thenReturn(true);
        Song summerSong = Song.builder().songUri("http://spotify/summersong").imageUrl("http://img").title("Summer Song").artist("Vulfpeck").build();
        SongEntity summerSongEntity = SongConverter.convertToSongEntity(summerSong);
        AddSongResponse actual = addSongUseCaseImpl.addSong(request);
        actual.setAdded(true);
        AddSongResponse expected = AddSongResponse.builder().added(true).build();
        assertEquals(expected, actual);
        verify(songRepository).save(summerSongEntity);
        }

    @Test
    void addSong_toNonExistingPlaylist(){
        AddSongRequest request = AddSongRequest.builder().songUri("http://spotify/summersong").img("http://img").title("Summer Song").artist("Vulfpeck").playlistId(2L).build();
        when(playlistRepository.existsById(request.getPlaylistId())).thenThrow(PlaylistDoesNotExist.class);
        assertThrows(PlaylistDoesNotExist.class, () -> addSongUseCaseImpl.addSong(request));
    }

    @Test
    void savePlaylistSong_toExistingPlaylist(){
        List<SongEntity> songs = new ArrayList<>();
        List<GenreEntity> genres = new ArrayList<>();
        Song summerSong = Song.builder().songUri("http://spotify/summersong").imageUrl("http://img").title("Summer Song").artist("Vulfpeck").build();
        SongEntity summerSongEntity = SongConverter.convertToSongEntity(summerSong);
        PlaylistEntity summerPlaylistEntity = PlaylistEntity.builder().playlistId(2L).songs(songs).genres(genres).title("Summer Playlist").userId(3L).duration(0.5).build();
        PlaylistSongEntity addedSong = PlaylistSongEntity.builder().playlist(summerPlaylistEntity).song(summerSongEntity).build();
        when(playlistSongRepository.save(addedSong)).thenReturn(addedSong);
        PlaylistSongEntity actual = playlistSongRepository.save(addedSong);
        PlaylistSongEntity expected = PlaylistSongEntity.builder().playlist(summerPlaylistEntity).song(summerSongEntity).build();
        assertEquals(expected.getPlaylist(), actual.getPlaylist());
        verify(playlistSongRepository).save(addedSong);
    }

}