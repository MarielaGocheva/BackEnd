package com.example.individual.business.impl;

import com.example.individual.domain.GetAllPlaylistsByUserIdRequest;
import com.example.individual.domain.GetAllPlaylistsByUserIdResponse;
import com.example.individual.repository.PlaylistRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GetAllPlaylistsByUserIdUseCaseImplTest {
    @Mock
    private PlaylistRepository playlistRepositoryMock;

    @InjectMocks
    private GetAllPlaylistsByUserIdUseCaseImpl getAllPlaylistsUseCase;

    @Test
    void getAllPlaylistsByUserId_shouldReturnAllPlaylistsConverted(){
        GetAllPlaylistsByUserIdRequest request = new GetAllPlaylistsByUserIdRequest();
        request.setUserId(1L);

        GetAllPlaylistsByUserIdResponse response = getAllPlaylistsUseCase.getPlaylists(request);
        int actualResult = response.getPlaylists().size();
        int expectedResult = playlistRepositoryMock.findAll().size();

        assertEquals(expectedResult, actualResult);

        verify(playlistRepositoryMock).findAll();
    }
}