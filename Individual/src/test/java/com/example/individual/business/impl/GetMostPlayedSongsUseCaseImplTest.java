package com.example.individual.business.impl;

import com.example.individual.business.converter.SongConverter;
import com.example.individual.domain.GetMostPlayedSongsResponse;
import com.example.individual.domain.Song;
import com.example.individual.repository.SongRepository;
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
class GetMostPlayedSongsUseCaseImplTest {
    @Mock
    private SongRepository songRepositoryMock;
    @InjectMocks
    private GetMostPlayedSongsUseCaseImpl getMostPlayedSongsUseCase;

    @Test
    void getMostPlayedSongs(){
        List<Song> songs = new ArrayList<>();
        songs.add(Song.builder().title("Nice Song").artist("Alter Bridge").id(2L).build());
        songs.add(Song.builder().title("Nice Song2").artist("Alter Bridge2").id(3L).build());
        List<SongEntity> convertedSongs = songs.stream().map(SongConverter::convertToSongEntity).toList();
        when(songRepositoryMock.findTop20ByOrderByPlaysDesc()).thenReturn(convertedSongs);
        GetMostPlayedSongsResponse actual = getMostPlayedSongsUseCase.getMostPlayed();
        GetMostPlayedSongsResponse expected = GetMostPlayedSongsResponse.builder().mostPlayed(songs).build();
        assertEquals(expected.getMostPlayed().size(), actual.getMostPlayed().size());
        verify(songRepositoryMock).findTop20ByOrderByPlaysDesc();
    }
}