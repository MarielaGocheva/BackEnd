package com.example.individual.business.impl;

import com.example.individual.domain.GetAllPlaylistsRequest;
import com.example.individual.domain.GetAllPlaylistsResponse;
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
        GetAllPlaylistsRequest request = new GetAllPlaylistsRequest();
        request.setUserId(1L);

        GetAllPlaylistsResponse response = getAllPlaylistsUseCase.getPlaylists(request);
        int actualResult = response.getPlaylists().size();
        int expectedResult = playlistRepositoryMock.findAll().size();

        assertEquals(expectedResult, actualResult);

        verify(playlistRepositoryMock).findAll();
    }
}