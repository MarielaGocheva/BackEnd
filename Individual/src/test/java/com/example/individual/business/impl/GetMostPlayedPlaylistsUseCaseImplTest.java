package com.example.individual.business.impl;

import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.domain.Genre;
import com.example.individual.domain.GetMostPlayedPlaylistsResponse;
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
class GetMostPlayedPlaylistsUseCaseImplTest {
    @Mock
    private PlaylistRepository playlistRepositoryMock;
    @InjectMocks
    private GetMostPlayedPlaylistsUseCaseImpl getMostPlayedPlaylistsUseCase;

    @Test
    void getMostPlayed_valid(){
        List<Playlist> playlists = new ArrayList<>();
        List<Song> songs = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        Playlist summerPlaylist = Playlist.builder().id(2L).title("Summer Playlist").userId(2L).imageUrl("http://img").duration(0.5).songs(songs).genres(genres).build();
        Playlist gymPlaylist = Playlist.builder().id(3L).title("Gym Playlist").userId(3L).imageUrl("http://img").duration(0.5).songs(songs).genres(genres).build();
        playlists.add(summerPlaylist);
        playlists.add(gymPlaylist);
        List<PlaylistEntity> playlistEntities = playlists.stream().map(PlaylistConverter::convertToPlaylistEntity).toList();
        when(playlistRepositoryMock.findTop20ByOrderByPlaysDesc()).thenReturn(playlistEntities);
        GetMostPlayedPlaylistsResponse actual = getMostPlayedPlaylistsUseCase.getMostPlayed();
        GetMostPlayedPlaylistsResponse expected = GetMostPlayedPlaylistsResponse.builder().mostPlayed(playlists).build();
        assertEquals(expected.getMostPlayed().size(), actual.getMostPlayed().size());
        verify(playlistRepositoryMock).findTop20ByOrderByPlaysDesc();
    }

}