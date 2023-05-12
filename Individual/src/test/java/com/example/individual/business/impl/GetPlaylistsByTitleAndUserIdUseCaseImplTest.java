package com.example.individual.business.impl;

import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.business.exceptions.PlaylistDoesNotExist;
import com.example.individual.business.exceptions.UserNotFoundException;
import com.example.individual.domain.*;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.UserRepository;
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
class GetPlaylistsByTitleAndUserIdUseCaseImplTest {
    @Mock
    private PlaylistRepository playlistRepositoryMock;
    @Mock
    private UserRepository userRepositoryMock;
    @InjectMocks
    private GetPlaylistsByTitleAndUserIdUseCaseImpl getPlaylistsByTitleAndUserIdUseCase;

    @Test
    void getPlaylistsByTitleAndUserId_valid(){
        GetPlaylistsByTitleAndUserIdRequest request = GetPlaylistsByTitleAndUserIdRequest.builder().userId(2L).title("Summer Hits").build();
        List<Song> songs = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        Playlist summerPlaylist = Playlist.builder().id(2L).userId(2L).title("Summer Hits").songs(songs).genres(genres).build();
        PlaylistEntity summerPlaylistEntity = PlaylistConverter.convertToPlaylistEntity(summerPlaylist);
        when(userRepositoryMock.existsById(request.getUserId())).thenReturn(true);
        when(playlistRepositoryMock.existsByTitleAndUserId(request.getTitle(), request.getUserId())).thenReturn(true);
        when(playlistRepositoryMock.findByTitleAndUserId(request.getTitle(), request.getUserId())).thenReturn(summerPlaylistEntity);
        GetPlaylistsByTitleAndUserIdResponse actual = getPlaylistsByTitleAndUserIdUseCase.getPlaylists(request);
        GetPlaylistsByTitleAndUserIdResponse expected = GetPlaylistsByTitleAndUserIdResponse.builder().playlist(summerPlaylist).build();
        assertEquals(expected.getPlaylist().getTitle(), actual.getPlaylist().getTitle());
        verify(playlistRepositoryMock).findByTitleAndUserId(request.getTitle(), request.getUserId());
    }

    @Test
    void getPlaylistsByTitleAndUserId_userDoesNotExist(){
        GetPlaylistsByTitleAndUserIdRequest request = GetPlaylistsByTitleAndUserIdRequest.builder().userId(2L).title("Summer Hits").build();
        assertThrows(UserNotFoundException.class, () -> getPlaylistsByTitleAndUserIdUseCase.getPlaylists(request));
    }

    @Test
    void getPlaylistsByTitleAndUserId_userIsEmpty(){
        GetPlaylistsByTitleAndUserIdRequest request = GetPlaylistsByTitleAndUserIdRequest.builder().userId(null).title("Summer Hits").build();
        assertThrows(UserNotFoundException.class, () -> getPlaylistsByTitleAndUserIdUseCase.getPlaylists(request));
    }

    @Test
    void getPlaylistsByTitleAndUserUd_playlistTitleDoesNotExist(){
        GetPlaylistsByTitleAndUserIdRequest request = GetPlaylistsByTitleAndUserIdRequest.builder().userId(2L).title("Summer Hits").build();
        when(userRepositoryMock.existsById(request.getUserId())).thenReturn(true);
        assertThrows(PlaylistDoesNotExist.class, () -> getPlaylistsByTitleAndUserIdUseCase.getPlaylists(request));
    }

    @Test
    void getPlaylistsByTitleAndUserUd_playlistTitleIsEmpty(){
        GetPlaylistsByTitleAndUserIdRequest request = GetPlaylistsByTitleAndUserIdRequest.builder().userId(2L).title("").build();
        when(userRepositoryMock.existsById(request.getUserId())).thenReturn(true);
        assertThrows(PlaylistDoesNotExist.class, () -> getPlaylistsByTitleAndUserIdUseCase.getPlaylists(request));
    }
}