package com.example.individual.business.impl;

import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.domain.CreatePlaylistRequest;
import com.example.individual.domain.CreatePlaylistResponse;
import com.example.individual.domain.Playlist;
import com.example.individual.domain.Song;
import com.example.individual.repository.PlaylistRepository;
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
class CreatePlaylistUseCaseImplTest {
    @Mock
    PlaylistRepository playlistRepositoryMock;
    @InjectMocks
    private CreatePlaylistUseCaseImpl createPlaylistUseCase;

    @Test
    void createPlaylist_valid(){
//        List<Song> songs = new ArrayList<>();
//        Song song = Song.builder().build();
//        songs.add(song);
//        Playlist playlist = Playlist.builder().userId(1L).title("Christmas Songs").songs(songs).build();
//        CreatePlaylistRequest request = CreatePlaylistRequest.builder().userId(1L).name("Christmas Songs").build();
//        when(playlistRepositoryMock.findByPlaylistId(1L)).thenReturn(PlaylistConverter.convertToPlaylistEntity(playlist));
//        CreatePlaylistResponse actual = createPlaylistUseCase.createPlaylist(request);
//        CreatePlaylistResponse expected = CreatePlaylistResponse.builder().playlist(playlist).build();
//        assertEquals(expected.getPlaylist(), actual.getPlaylist());
//        verify(playlistRepositoryMock).findByPlaylistId(1L);
    }
}