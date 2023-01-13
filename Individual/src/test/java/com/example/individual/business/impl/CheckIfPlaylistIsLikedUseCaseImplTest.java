package com.example.individual.business.impl;

import com.example.individual.business.exceptions.PlaylistDoesNotExist;
import com.example.individual.business.exceptions.UserNotFoundException;
import com.example.individual.domain.CheckIfPlaylistIsLikedRequest;
import com.example.individual.domain.CheckIfPlaylistIsLikedResponse;
import com.example.individual.repository.LikedPlaylistsRepository;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.UserRepository;
import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CheckIfPlaylistIsLikedUseCaseImplTest {
    @Mock
    private LikedPlaylistsRepository likedPlaylistsRepository;
    @Mock
    private PlaylistRepository playlistRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private CheckIfPlaylistIsLikedUseCaseImpl checkIfPlaylistIsLikedUseCase;

    @Test
    void checkIfLiked_liked_valid(){
        CheckIfPlaylistIsLikedRequest request = CheckIfPlaylistIsLikedRequest.builder().userId(2L).playlistId(4L).build();
        UserEntity user = UserEntity.builder().fName("Mariela").lName("Gocheva").email("mariela@gmail").password("123").build();
        PlaylistEntity playlist = PlaylistEntity.builder().playlistId(4L).userId(2L).title("PartyPlaylist").build();
        when(userRepository.existsById(request.getUserId())).thenReturn(true);
        when(playlistRepository.existsById(request.getPlaylistId())).thenReturn(true);
        when(userRepository.findUserById(request.getUserId())).thenReturn(user);
        when(playlistRepository.findByPlaylistId(request.getPlaylistId())).thenReturn(playlist);
        when(likedPlaylistsRepository.existsByUserAndPlaylist(user, playlist)).thenReturn(true);
        CheckIfPlaylistIsLikedResponse actual = checkIfPlaylistIsLikedUseCase.checkIfLiked(request);
        CheckIfPlaylistIsLikedResponse expected = CheckIfPlaylistIsLikedResponse.builder().liked(true).build();
        assertEquals(expected.getLiked(), actual.getLiked());
        verify(likedPlaylistsRepository).existsByUserAndPlaylist(user, playlist);
    }

    @Test
    void checkIfLiked_notLiked_valid(){
        CheckIfPlaylistIsLikedRequest request = CheckIfPlaylistIsLikedRequest.builder().userId(2L).playlistId(4L).build();
        UserEntity user = UserEntity.builder().fName("Mariela").lName("Gocheva").email("mariela@gmail").password("123").build();
        PlaylistEntity playlist = PlaylistEntity.builder().playlistId(4L).userId(2L).title("PartyPlaylist").build();
        when(userRepository.existsById(request.getUserId())).thenReturn(true);
        when(playlistRepository.existsById(request.getPlaylistId())).thenReturn(true);
        when(userRepository.findUserById(request.getUserId())).thenReturn(user);
        when(playlistRepository.findByPlaylistId(request.getPlaylistId())).thenReturn(playlist);
        when(likedPlaylistsRepository.existsByUserAndPlaylist(user, playlist)).thenReturn(false);
        CheckIfPlaylistIsLikedResponse actual = checkIfPlaylistIsLikedUseCase.checkIfLiked(request);
        CheckIfPlaylistIsLikedResponse expected = CheckIfPlaylistIsLikedResponse.builder().liked(false).build();
        assertEquals(expected.getLiked(), actual.getLiked());
        verify(likedPlaylistsRepository).existsByUserAndPlaylist(user, playlist);
    }

    @Test
    void checkIfLiked_userDoesNotExist(){
        CheckIfPlaylistIsLikedRequest request = CheckIfPlaylistIsLikedRequest.builder().userId(2L).playlistId(4L).build();
        assertThrows(UserNotFoundException.class, () -> checkIfPlaylistIsLikedUseCase.checkIfLiked(request));
    }

    @Test
    void checkIfLiked_userIsEmpty(){
        CheckIfPlaylistIsLikedRequest request = CheckIfPlaylistIsLikedRequest.builder().userId(null).playlistId(4L).build();
        assertThrows(UserNotFoundException.class, () -> checkIfPlaylistIsLikedUseCase.checkIfLiked(request));
    }

    @Test
    void checkIfLiked_playlistDoesNotExist(){
        CheckIfPlaylistIsLikedRequest request = CheckIfPlaylistIsLikedRequest.builder().userId(2L).playlistId(4L).build();
        when(userRepository.existsById(request.getUserId())).thenReturn(true);
        assertThrows(PlaylistDoesNotExist.class, () -> checkIfPlaylistIsLikedUseCase.checkIfLiked(request));
    }

    @Test
    void checkIfLiked_playlistEmpty(){
        CheckIfPlaylistIsLikedRequest request = CheckIfPlaylistIsLikedRequest.builder().userId(2L).playlistId(null).build();
        when(userRepository.existsById(request.getUserId())).thenReturn(true);
        assertThrows(PlaylistDoesNotExist.class, () -> checkIfPlaylistIsLikedUseCase.checkIfLiked(request));
    }
}