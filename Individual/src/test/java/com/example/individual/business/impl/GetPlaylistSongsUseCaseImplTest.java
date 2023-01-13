package com.example.individual.business.impl;

import com.example.individual.business.converter.SongConverter;
import com.example.individual.business.exceptions.PlaylistDoesNotExist;
import com.example.individual.domain.GetPlaylistSongsRequest;
import com.example.individual.domain.GetPlaylistSongsResponse;
import com.example.individual.domain.Song;
import com.example.individual.repository.PlaylistRepository;
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
class GetPlaylistSongsUseCaseImplTest {
    @Mock
    private PlaylistRepository playlistRepositoryMock;
    @InjectMocks
    private GetPlaylistSongsUseCaseImpl getPlaylistSongsUseCase;

    @Test
    void getSongs_valid(){
        GetPlaylistSongsRequest request = GetPlaylistSongsRequest.builder().playlistId(3L).build();
        List<Song> songs = new ArrayList<>();
        songs.add(Song.builder().title("Another Love").build());
        songs.add(Song.builder().title("Breaking The Law").build());
        List<SongEntity> songEntities = songs.stream().map(SongConverter::convertToSongEntity).toList();
        PlaylistEntity playlist = PlaylistEntity.builder().playlistId(2L).userId(2L).title("Summer Hits").songs(songEntities).build();
        when(playlistRepositoryMock.existsById(request.getPlaylistId())).thenReturn(true);
        when(playlistRepositoryMock.findByPlaylistId(request.getPlaylistId())).thenReturn(playlist);
        GetPlaylistSongsResponse actual = getPlaylistSongsUseCase.getSongs(request);
        GetPlaylistSongsResponse expected = GetPlaylistSongsResponse.builder().songs(songs).build();
        assertEquals(expected.getSongs().size(), actual.getSongs().size());
        verify(playlistRepositoryMock).findByPlaylistId(request.getPlaylistId());
    }

    @Test
    void getSongs_playlistDoesNotExist(){
        GetPlaylistSongsRequest request = GetPlaylistSongsRequest.builder().playlistId(3L).build();
        assertThrows(PlaylistDoesNotExist.class, () -> getPlaylistSongsUseCase.getSongs(request));
    }

    @Test
    void getSongs_playlistIsEmpty(){
        GetPlaylistSongsRequest request = GetPlaylistSongsRequest.builder().playlistId(null).build();
        assertThrows(PlaylistDoesNotExist.class, () -> getPlaylistSongsUseCase.getSongs(request));
    }
}