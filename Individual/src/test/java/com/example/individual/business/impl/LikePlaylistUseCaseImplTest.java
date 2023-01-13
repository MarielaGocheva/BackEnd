package com.example.individual.business.impl;

import com.example.individual.business.exceptions.PlaylistDoesNotExist;
import com.example.individual.business.exceptions.UserNotFoundException;
import com.example.individual.domain.LikePlaylistRequest;
import com.example.individual.repository.LikedPlaylistsRepository;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.UserRepository;
import com.example.individual.repository.entity.LikedPlaylistsEntity;
import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.UserEntity;
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
class LikePlaylistUseCaseImplTest {
    @Mock
    private LikedPlaylistsRepository likedPlaylistsRepositoryMock;
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private PlaylistRepository playlistRepositoryMock;
    @InjectMocks
    private LikePlaylistUseCaseImpl likePlaylistUseCase;

    @Test
    void likePlaylist_valid(){
        LikePlaylistRequest request = LikePlaylistRequest.builder().playlistId(2L).userId(2L).build();
        PlaylistEntity playlistEntity = PlaylistEntity.builder().playlistId(1L).userId(2L).title("Christmas").songs(new ArrayList<>()).build();
        UserEntity user = UserEntity.builder().id(2L).fName("Stela").lName("Ivanova").email("sela@gmail").role("DJ").build();
        LikedPlaylistsEntity likedPlaylist = LikedPlaylistsEntity.builder().playlist(playlistEntity).user(user).build();
        when(userRepositoryMock.existsById(request.getUserId())).thenReturn(true);
        when(playlistRepositoryMock.existsById(request.getPlaylistId())).thenReturn(true);
        when(playlistRepositoryMock.findByPlaylistId(request.getPlaylistId())).thenReturn(playlistEntity);
        when(userRepositoryMock.findUserById(request.getUserId())).thenReturn(user);
        likePlaylistUseCase.likePlaylist(request);
        verify(likedPlaylistsRepositoryMock).save(likedPlaylist);
    }

    @Test
    void likePlaylist_userDoesNotExist(){
        LikePlaylistRequest request = LikePlaylistRequest.builder().playlistId(2L).userId(2L).build();
        assertThrows(UserNotFoundException.class, () -> likePlaylistUseCase.likePlaylist(request));
    }

    @Test
    void likePlaylist_userIsNull(){
        LikePlaylistRequest request = LikePlaylistRequest.builder().playlistId(2L).userId(null).build();
        assertThrows(UserNotFoundException.class, () -> likePlaylistUseCase.likePlaylist(request));
    }

    @Test
    void likPlaylist_playlistDoesNotExist(){
        LikePlaylistRequest request = LikePlaylistRequest.builder().playlistId(2L).userId(2L).build();
        when(userRepositoryMock.existsById(request.getUserId())).thenReturn(true);
        assertThrows(PlaylistDoesNotExist.class, () -> likePlaylistUseCase.likePlaylist(request));
    }

    @Test
    void likPlaylist_playlistIsNull(){
        LikePlaylistRequest request = LikePlaylistRequest.builder().playlistId(null).userId(2L).build();
        when(userRepositoryMock.existsById(request.getUserId())).thenReturn(true);
        assertThrows(PlaylistDoesNotExist.class, () -> likePlaylistUseCase.likePlaylist(request));
    }
}