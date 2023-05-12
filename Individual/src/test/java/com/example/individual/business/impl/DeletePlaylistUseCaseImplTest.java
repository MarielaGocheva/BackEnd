package com.example.individual.business.impl;

import com.example.individual.business.exceptions.PlaylistDoesNotExist;
import com.example.individual.domain.DeletePlaylistRequest;
import com.example.individual.domain.DeletePlaylistResponse;
import com.example.individual.repository.PlaylistRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeletePlaylistUseCaseImplTest {
    @Mock
    private PlaylistRepository playlistRepositoryMock;
    @InjectMocks
    private DeletePlaylistUseCaseImpl deletePlaylistUseCase;

    @Test
    void deletePlaylist_playlistExists(){
        DeletePlaylistRequest request = DeletePlaylistRequest.builder().playlistId(2L).build();
        when(playlistRepositoryMock.existsById(request.getPlaylistId())).thenReturn(true);
        DeletePlaylistResponse actual = deletePlaylistUseCase.deletePlaylist(request);
        DeletePlaylistResponse expected = DeletePlaylistResponse.builder().deleted(true).build();
        assertEquals(expected, actual);
        verify(playlistRepositoryMock).deleteById(request.getPlaylistId());
    }

    @Test
    void deletePlaylist_playlistDoesNotExist(){
        DeletePlaylistRequest request = DeletePlaylistRequest.builder().playlistId(2L).build();
        when(playlistRepositoryMock.existsById(request.getPlaylistId())).thenThrow(PlaylistDoesNotExist.class);
        assertThrows(PlaylistDoesNotExist.class, () -> deletePlaylistUseCase.deletePlaylist(request));
    }

    @Test
    void deletePlaylist_emptyRequest(){
        DeletePlaylistRequest request = DeletePlaylistRequest.builder().playlistId(null).build();
        assertThrows(PlaylistDoesNotExist.class, () -> deletePlaylistUseCase.deletePlaylist(request));
    }
}