package com.example.individual.business.impl;

import com.example.individual.business.exceptions.PlaylistDoesNotExist;
import com.example.individual.business.exceptions.SongDoesNotExist;
import com.example.individual.domain.DeleteSongRequest;
import com.example.individual.domain.DeleteSongResponse;
import com.example.individual.repository.PlaylistSongRepository;
import com.example.individual.repository.SongRepository;
import com.example.individual.repository.entity.SongEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteSongUseCaseImplTest {
    @Mock
    private SongRepository songRepositoryMock;
    @Mock
    private PlaylistSongRepository playlistSongRepositoryMock;
    @InjectMocks DeleteSongUseCaseImpl deleteSongUseCase;

    @Test
    void deleteSong_valid(){
        DeleteSongRequest request = DeleteSongRequest.builder().songUri("http://spotify/summersong").playlistId(2L).build();
        SongEntity songEntity = SongEntity.builder().songUri("http://spotify/summersong").songId(2L).title("Summer song").build();
        when(songRepositoryMock.findBySongUri(request.getSongUri())).thenReturn(songEntity);
        DeleteSongResponse actual = deleteSongUseCase.deleteSong(request);
        DeleteSongResponse expected = DeleteSongResponse.builder().deleted(true).build();
        assertEquals(expected, actual);
    }

    @Test
    void deleteSong_emptySongUri(){
        DeleteSongRequest request = DeleteSongRequest.builder().songUri("").playlistId(2L).build();
        assertThrows(SongDoesNotExist.class, () -> deleteSongUseCase.deleteSong(request));
    }

    @Test
    void deleteSong_songUriDoesNotExist(){
        DeleteSongRequest request = DeleteSongRequest.builder().songUri("http://song").playlistId(2L).build();
        when(songRepositoryMock.findBySongUri(request.getSongUri())).thenThrow(SongDoesNotExist.class);
        assertThrows(SongDoesNotExist.class, () -> deleteSongUseCase.deleteSong(request));
    }

    @Test
    void deleteSong_emptyPlaylistId(){
        DeleteSongRequest request = DeleteSongRequest.builder().songUri("http://spotify/summersong").playlistId(null).build();
        SongEntity songEntity = SongEntity.builder().songUri("http://spotify/summersong").songId(2L).title("Summer song").build();
        when(songRepositoryMock.findBySongUri(request.getSongUri())).thenReturn(songEntity);
        assertThrows(PlaylistDoesNotExist.class, () -> deleteSongUseCase.deleteSong(request));
    }

    @Test
    void deleteSong_playlistDoesNotExist(){
        DeleteSongRequest request = DeleteSongRequest.builder().songUri("http://spotify/summersong").playlistId(3L).build();
        SongEntity songEntity = SongEntity.builder().songUri("http://spotify/summersong").songId(2L).title("Summer song").build();
        when(songRepositoryMock.findBySongUri(request.getSongUri())).thenReturn(songEntity);
        when(playlistSongRepositoryMock.existsByPlaylist(request.getPlaylistId())).thenThrow(PlaylistDoesNotExist.class);
        assertThrows(PlaylistDoesNotExist.class, () -> deleteSongUseCase.deleteSong(request));
    }
}