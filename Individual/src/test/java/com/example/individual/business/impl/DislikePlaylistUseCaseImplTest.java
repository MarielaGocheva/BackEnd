package com.example.individual.business.impl;

import com.example.individual.business.exceptions.PlaylistDoesNotExist;
import com.example.individual.business.exceptions.UserNotFoundException;
import com.example.individual.domain.DislikePlaylistRequest;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DislikePlaylistUseCaseImplTest {
    @Mock
    private LikedPlaylistsRepository likedPlaylistsRepository;
    @Mock
    private PlaylistRepository playlistRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private DislikePlaylistUseCaseImpl dislikePlaylistUseCase;

    @Test
    void dislikePlaylist_valid(){
        DislikePlaylistRequest request = DislikePlaylistRequest.builder().userId(2L).playlistId(4L).build();
        UserEntity user = UserEntity.builder().fName("Mariela").lName("Gocheva").email("mariela@gmail").password("123").build();
        PlaylistEntity playlist = PlaylistEntity.builder().playlistId(4L).userId(2L).title("PartyPlaylist").build();
        when(userRepository.existsById(request.getUserId())).thenReturn(true);
        when(playlistRepository.existsById(request.getPlaylistId())).thenReturn(true);
        when(userRepository.findUserById(request.getUserId())).thenReturn(user);
        when(playlistRepository.findByPlaylistId(request.getPlaylistId())).thenReturn(playlist);
        dislikePlaylistUseCase.dislikePlaylist(request);
        verify(likedPlaylistsRepository).deleteByUserAndPlaylist(user, playlist);
    }

    @Test
    void dislikePlaylist_userDoesNotExist(){
        DislikePlaylistRequest request = DislikePlaylistRequest.builder().userId(2L).playlistId(4L).build();
        assertThrows(UserNotFoundException.class, () -> dislikePlaylistUseCase.dislikePlaylist(request));
    }

    @Test
    void dislikePlaylist_userIsEmpty(){
        DislikePlaylistRequest request = DislikePlaylistRequest.builder().userId(null).playlistId(4L).build();
        assertThrows(UserNotFoundException.class, () -> dislikePlaylistUseCase.dislikePlaylist(request));
    }

    @Test
    void checkIfLiked_playlistDoesNotExist(){
        DislikePlaylistRequest request = DislikePlaylistRequest.builder().userId(2L).playlistId(4L).build();
        when(userRepository.existsById(request.getUserId())).thenReturn(true);
        assertThrows(PlaylistDoesNotExist.class, () -> dislikePlaylistUseCase.dislikePlaylist(request));
    }

    @Test
    void checkIfLiked_playlistEmpty(){
        DislikePlaylistRequest request = DislikePlaylistRequest.builder().userId(2L).playlistId(null).build();
        when(userRepository.existsById(request.getUserId())).thenReturn(true);
        assertThrows(PlaylistDoesNotExist.class, () -> dislikePlaylistUseCase.dislikePlaylist(request));
    }
}