package com.example.individual.business.impl;

import com.example.individual.business.exceptions.PlaylistDoesNotExist;
import com.example.individual.domain.Playlist;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.entity.PlaylistEntity;
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
class GetPlaylistUseCaseImplTest {
    @Mock
    private PlaylistRepository playlistRepository;
    @InjectMocks
    private GetPlaylistUseCaseImpl getPlaylistUseCase;

    @Test
    void getPlaylist_valid(){
        Long playlistId = 4L;
        PlaylistEntity playlistEntity = PlaylistEntity.builder().playlistId(2L).userId(2L).title("Summer Hits").songs(new ArrayList<>()).build();
        when(playlistRepository.existsById(playlistId)).thenReturn(true);
        when(playlistRepository.findByPlaylistId(playlistId)).thenReturn(playlistEntity);
        Playlist actual = getPlaylistUseCase.getPlaylist(playlistId);
        Playlist expected = Playlist.builder().id(2L).userId(2L).title("Summer Hits").songs(new ArrayList<>()).build();
        assertEquals(expected.getTitle(), actual.getTitle());
        verify(playlistRepository).findByPlaylistId(playlistId);
    }

    @Test
    void getPlaylist_playlistDoesNotExist(){
        Long playlistId = 4L;
        assertThrows(PlaylistDoesNotExist.class, () -> getPlaylistUseCase.getPlaylist(playlistId));
    }

    @Test
    void getPlaylist_playlistIsNull(){
        Long playlistId = null;
        assertThrows(PlaylistDoesNotExist.class, () -> getPlaylistUseCase.getPlaylist(playlistId));
    }
}