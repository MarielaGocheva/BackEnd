package com.example.individual.business.impl;

import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.domain.Genre;
import com.example.individual.domain.GetAllPlaylistsResponse;
import com.example.individual.domain.Playlist;
import com.example.individual.domain.Song;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.entity.PlaylistEntity;
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
class GetAllPlaylistsUseCaseImplTest {
    @Mock
    private PlaylistRepository playlistRepositoryMock;
    @InjectMocks
    private GetAllPlaylistsUseCaseImpl getAllPlaylistsUseCaseImpl;

    @Test
    void getAllPlaylists(){
        List<Song> songs = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        Playlist summerPlaylist = Playlist.builder().id(2L).title("Summer Playlist").userId(2L).imageUrl("http://img").duration(0.5).songs(songs).genres(genres).build();
        Playlist gymPlaylist = Playlist.builder().id(3L).title("Gym Playlist").userId(3L).imageUrl("http://img").duration(0.5).songs(songs).genres(genres).build();
        PlaylistEntity summerPlaylistEntity = PlaylistConverter.convertToPlaylistEntity(summerPlaylist);
        PlaylistEntity gymPlaylistEntity = PlaylistConverter.convertToPlaylistEntity(gymPlaylist);
        when(playlistRepositoryMock.findAll()).thenReturn(List.of(summerPlaylistEntity, gymPlaylistEntity));
        GetAllPlaylistsResponse actual = getAllPlaylistsUseCaseImpl.getPlaylists();
        GetAllPlaylistsResponse expected = GetAllPlaylistsResponse.builder().playlists(List.of(summerPlaylist, gymPlaylist)).build();
        assertEquals(expected.getPlaylists().get(0).getTitle(), actual.getPlaylists().get(0).getTitle());
        verify(playlistRepositoryMock).findAll();
    }

}