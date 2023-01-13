package com.example.individual.business.impl;

import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.business.converter.SongConverter;
import com.example.individual.business.exceptions.InsufficientNumberOfLikedPlaylists;
import com.example.individual.business.exceptions.UserNotFoundException;
import com.example.individual.domain.*;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.RecentlyPlayedRepository;
import com.example.individual.repository.SongRepository;
import com.example.individual.repository.UserRepository;
import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.RecentlyPlayedEntity;
import com.example.individual.repository.entity.SongEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetRecentlyPlayedUseCaseImplTest {
    @Mock
    private SongRepository songRepositoryMock;
    @Mock
    private PlaylistRepository playlistRepositoryMock;
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private RecentlyPlayedRepository recentlyPlayedRepositoryMock;
    @InjectMocks
    private GetRecentlyPlayedUseCaseImpl getRecentlyPlayedUseCase;

    @Test
    void getRecentlyPlayed_valid_withoutPlaylist(){
        GetRecentlyPlayedRequest request = GetRecentlyPlayedRequest.builder().userId(2L).build();
        Song summerSong = Song.builder().title("Nice Song").artist("Alter Bridge").id(2L).build();
        SongEntity summerSongEntity = SongConverter.convertToSongEntity(summerSong);
        RecentlyPlayedEntity recentlyPlayed = RecentlyPlayedEntity.builder().userId(2L).songId(3L).playlistId(null).build();
        when(userRepositoryMock.existsById(request.getUserId())).thenReturn(true);
        when(recentlyPlayedRepositoryMock.findTop1ByUserIdOrderByIdDesc(request.getUserId())).thenReturn(recentlyPlayed);
        when(songRepositoryMock.findBySongId(recentlyPlayed.getSongId())).thenReturn(summerSongEntity);
        GetRecentlyPlayedResponse actual = getRecentlyPlayedUseCase.getRecentlyPlayed(request);
        GetRecentlyPlayedResponse expected = GetRecentlyPlayedResponse.builder().song(summerSong).playlist(null).build();
        assertEquals(expected.getSong().getTitle(), actual.getSong().getTitle());
        verify(songRepositoryMock).findBySongId(recentlyPlayed.getSongId());
    }

    @Test
    void getRecentlyPlayed_valid(){
        GetRecentlyPlayedRequest request = GetRecentlyPlayedRequest.builder().userId(2L).build();
        Playlist summerPlaylist = Playlist.builder().id(2L).title("Summer Playlist").userId(2L)
                .imageUrl("http://img").duration(0.5).songs(new ArrayList<>()).genres(new ArrayList<>()).build();
        PlaylistEntity summerPlaylistEntity = PlaylistConverter.convertToPlaylistEntity(summerPlaylist);
        Song summerSong = Song.builder().title("Nice Song").artist("Alter Bridge").id(2L).build();
        SongEntity summerSongEntity = SongConverter.convertToSongEntity(summerSong);
        RecentlyPlayedEntity recentlyPlayed = RecentlyPlayedEntity.builder().userId(2L).songId(3L).playlistId(2L).build();
        when(userRepositoryMock.existsById(request.getUserId())).thenReturn(true);
        when(recentlyPlayedRepositoryMock.findTop1ByUserIdOrderByIdDesc(request.getUserId())).thenReturn(recentlyPlayed);
        when(playlistRepositoryMock.findByPlaylistId(recentlyPlayed.getPlaylistId())).thenReturn(summerPlaylistEntity);
        when(songRepositoryMock.findBySongId(recentlyPlayed.getSongId())).thenReturn(summerSongEntity);
        GetRecentlyPlayedResponse actual = getRecentlyPlayedUseCase.getRecentlyPlayed(request);
        GetRecentlyPlayedResponse expected = GetRecentlyPlayedResponse.builder().song(summerSong).playlist(summerPlaylist).build();
        assertEquals(expected.getPlaylist().getTitle(), actual.getPlaylist().getTitle());
        verify(songRepositoryMock).findBySongId(recentlyPlayed.getSongId());
    }

    @Test
    void getRecentlyPlayed_userDoesNotExist(){
        GetRecentlyPlayedRequest request = GetRecentlyPlayedRequest.builder().userId(2L).build();
        assertThrows(UserNotFoundException.class, () -> getRecentlyPlayedUseCase.getRecentlyPlayed(request));
    }

    @Test
    void getRecentlyPlayed_userIsNull(){
        GetRecentlyPlayedRequest request = GetRecentlyPlayedRequest.builder().userId(null).build();
        assertThrows(UserNotFoundException.class, () -> getRecentlyPlayedUseCase.getRecentlyPlayed(request));
    }

    @Test
    void getRecentlyPlayed_noHistory(){
        GetRecentlyPlayedRequest request = GetRecentlyPlayedRequest.builder().userId(2L).build();
        when(userRepositoryMock.existsById(request.getUserId())).thenReturn(true);
        when(recentlyPlayedRepositoryMock.findTop1ByUserIdOrderByIdDesc(request.getUserId())).thenReturn(null);
        assertThrows(NullPointerException.class, () -> getRecentlyPlayedUseCase.getRecentlyPlayed(request));
    }
}