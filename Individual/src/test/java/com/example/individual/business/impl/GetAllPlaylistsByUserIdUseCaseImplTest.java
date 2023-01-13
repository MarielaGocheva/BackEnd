package com.example.individual.business.impl;

import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.business.exceptions.UserNotFoundException;
import com.example.individual.domain.GetAllPlaylistsByUserIdRequest;
import com.example.individual.domain.GetAllPlaylistsByUserIdResponse;
import com.example.individual.domain.Playlist;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.UserRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllPlaylistsByUserIdUseCaseImplTest {
    @Mock
    private PlaylistRepository playlistRepositoryMock;
    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private GetAllPlaylistsByUserIdUseCaseImpl getAllPlaylistsByUserIdUseCase;

    @Test
    void getAllPlaylistsByUserId_valid(){
        GetAllPlaylistsByUserIdRequest request = GetAllPlaylistsByUserIdRequest.builder().userId(1L).build();
        List<SongEntity> songs = new ArrayList<>();
        List<PlaylistEntity> userPlaylistEntities = new ArrayList<>();
        userPlaylistEntities.add(PlaylistEntity.builder().userId(1L).songs(songs).build());
        userPlaylistEntities.add(PlaylistEntity.builder().userId(1L).songs(songs).build());
        List<Playlist> userPlaylists = userPlaylistEntities.stream().map(PlaylistConverter::convertToPlaylist).toList();
        when(userRepositoryMock.existsById(request.getUserId())).thenReturn(true);
        when(playlistRepositoryMock.findAllByUserId(request.getUserId())).thenReturn(userPlaylistEntities);
        GetAllPlaylistsByUserIdResponse actualResponse = getAllPlaylistsByUserIdUseCase.getPlaylists(request);
        GetAllPlaylistsByUserIdResponse expectedResponse = GetAllPlaylistsByUserIdResponse.builder().playlists(userPlaylists).build();
        int actual= actualResponse.getPlaylists().size();
        int expected = expectedResponse.getPlaylists().size();
        assertEquals(expected, actual);
        verify(playlistRepositoryMock).findAllByUserId(request.getUserId());
    }

    @Test
    void getAllPlaylistsByUserId_emptyUserId(){
        GetAllPlaylistsByUserIdRequest request = GetAllPlaylistsByUserIdRequest.builder().userId(null).build();
        assertThrows(UserNotFoundException.class, () -> getAllPlaylistsByUserIdUseCase.getPlaylists(request));
    }

    @Test
    void getAllPlaylistsByUserId_userDoesNotExist(){
        GetAllPlaylistsByUserIdRequest request = GetAllPlaylistsByUserIdRequest.builder().userId(2L).build();
        when(userRepositoryMock.existsById(request.getUserId())).thenThrow(UserNotFoundException.class);
        assertThrows(UserNotFoundException.class, () -> getAllPlaylistsByUserIdUseCase.getPlaylists(request));
    }

    @Test
    void getAllPlaylistsByUserId_userHasNoPlaylists(){
        GetAllPlaylistsByUserIdRequest request = GetAllPlaylistsByUserIdRequest.builder().userId(1L).build();
        when(userRepositoryMock.existsById(request.getUserId())).thenReturn(true);
        when(playlistRepositoryMock.findAllByUserId(request.getUserId())).thenReturn(new ArrayList<>());
        GetAllPlaylistsByUserIdResponse actual = getAllPlaylistsByUserIdUseCase.getPlaylists(request);
        GetAllPlaylistsByUserIdResponse expected = GetAllPlaylistsByUserIdResponse.builder().playlists(new ArrayList<>()).build();
        assertEquals(expected.getPlaylists(), actual.getPlaylists());
        verify(playlistRepositoryMock).findAllByUserId(request.getUserId());
    }
}