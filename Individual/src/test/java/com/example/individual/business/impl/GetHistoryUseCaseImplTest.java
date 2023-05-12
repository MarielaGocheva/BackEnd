package com.example.individual.business.impl;

import com.example.individual.business.converter.SongConverter;
import com.example.individual.business.exceptions.UserNotFoundException;
import com.example.individual.domain.GetHistoryRequest;
import com.example.individual.domain.GetHistoryResponse;
import com.example.individual.domain.Song;
import com.example.individual.repository.RecentlyPlayedRepository;
import com.example.individual.repository.SongRepository;
import com.example.individual.repository.UserRepository;
import com.example.individual.repository.entity.RecentlyPlayedEntity;
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
class GetHistoryUseCaseImplTest {
    @Mock
    private RecentlyPlayedRepository recentlyPlayedRepositoryMock;
    @Mock
    private SongRepository songRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private GetHistoryUseCaseImpl getHistoryUseCase;

    @Test
    void getHistory_valid(){
        GetHistoryRequest request = GetHistoryRequest.builder().userId(2L).build();
        List<SongEntity> recentlyPlayed = new ArrayList<>();
        SongEntity song1 = SongEntity.builder().songId(7L).songUri("http://songUri").title("Summer Song").build();
        SongEntity song2 = SongEntity.builder().songId(5L).songUri("http://songUri2").title("Party Song").build();
        SongEntity song3 = SongEntity.builder().songId(6L).songUri("http://songUri3").title("Lazy Song").build();
        recentlyPlayed.add(song1);
        recentlyPlayed.add(song2);
        recentlyPlayed.add(song3);
        List<Song> recentlyPlayedSongs = recentlyPlayed.stream().map(SongConverter::convertToSong).toList();
        List<RecentlyPlayedEntity> history = new ArrayList<>();
        history.add(RecentlyPlayedEntity.builder().userId(2L).songId(5L).playlistId(3L).build());
        history.add(RecentlyPlayedEntity.builder().userId(2L).songId(6L).playlistId(4L).build());
        history.add(RecentlyPlayedEntity.builder().userId(2L).songId(7L).playlistId(8L).build());
        when(userRepository.existsById(request.getUserId())).thenReturn(true);
        when(recentlyPlayedRepositoryMock.findDistinctByUserIdOrderByIdDesc(request.getUserId())).thenReturn(history);
        when(songRepository.findBySongId(5L)).thenReturn(song2);
        when(songRepository.findBySongId(6L)).thenReturn(song3);
        when(songRepository.findBySongId(7L)).thenReturn(song1);
        GetHistoryResponse actual = getHistoryUseCase.getHistory(request);
        GetHistoryResponse expected = GetHistoryResponse.builder().recentlyPlayed(recentlyPlayedSongs).build();
        assertEquals(expected.getRecentlyPlayed().size(), actual.getRecentlyPlayed().size());
        verify(recentlyPlayedRepositoryMock).findDistinctByUserIdOrderByIdDesc(request.getUserId());
    }

    @Test
    void getHistory_emptyUserId(){
        GetHistoryRequest request = GetHistoryRequest.builder().userId(null).build();
        assertThrows(UserNotFoundException.class, () -> getHistoryUseCase.getHistory(request));
    }

    @Test
    void getHistory_userDoesNotExist(){
        GetHistoryRequest request = GetHistoryRequest.builder().userId(3L).build();
        when(userRepository.existsById(request.getUserId())).thenReturn(false);
        assertThrows(UserNotFoundException.class, () -> getHistoryUseCase.getHistory(request));
    }
}