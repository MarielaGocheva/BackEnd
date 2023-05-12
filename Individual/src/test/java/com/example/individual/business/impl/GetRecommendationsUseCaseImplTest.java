package com.example.individual.business.impl;

import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.business.exceptions.UserNotFoundException;
import com.example.individual.domain.GetMostPlayedSongsResponse;
import com.example.individual.domain.GetRecommendationsRequest;
import com.example.individual.domain.GetRecommendationsResponse;
import com.example.individual.domain.Playlist;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.UserRepository;
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
class GetRecommendationsUseCaseImplTest {
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private PlaylistRepository playlistRepositoryMock;
    @InjectMocks
    private GetRecommendationsUseCaseImpl getRecommendationsUseCase;

    @Test
    void getRecommendations_valid_threeGenres(){
        GetRecommendationsRequest request = GetRecommendationsRequest.builder().userId(2L).build();
        List<Playlist> playlists = new ArrayList<>();
        playlists.add(Playlist.builder().id(1L).userId(2L).title("Christmas").songs(new ArrayList<>()).genres(new ArrayList<>()).build());
        playlists.add(Playlist.builder().id(2L).userId(2L).title("Party").songs(new ArrayList<>()).genres(new ArrayList<>()).build());
        playlists.add(Playlist.builder().id(3L).userId(2L).title("Lazy").songs(new ArrayList<>()).genres(new ArrayList<>()).build());
        List<Long> mostLikedGenres = new ArrayList<>();
        mostLikedGenres.add(2L);
        mostLikedGenres.add(16L);
        mostLikedGenres.add(8L);
        List<Long> recommendedPlaylists = new ArrayList<>();
        recommendedPlaylists.add(1L);
        recommendedPlaylists.add(2L);
        recommendedPlaylists.add(3L);
        when(userRepositoryMock.existsById(request.getUserId())).thenReturn(true);
        when(playlistRepositoryMock.findMostLikedGenres(request.getUserId())).thenReturn(mostLikedGenres);
        when(playlistRepositoryMock.findBestMatchingPlaylists(mostLikedGenres.get(0), mostLikedGenres.get(1), mostLikedGenres.get(2), request.getUserId()))
                .thenReturn(recommendedPlaylists);
        when(playlistRepositoryMock.findByPlaylistId(1L)).thenReturn(PlaylistConverter.convertToPlaylistEntity(playlists.get(0)));
        when(playlistRepositoryMock.findByPlaylistId(2L)).thenReturn(PlaylistConverter.convertToPlaylistEntity(playlists.get(1)));
        when(playlistRepositoryMock.findByPlaylistId(3L)).thenReturn(PlaylistConverter.convertToPlaylistEntity(playlists.get(2)));
        GetRecommendationsResponse actual = getRecommendationsUseCase.getRecommendations(request);
        GetRecommendationsResponse expected = GetRecommendationsResponse.builder().recommended(playlists).build();
        assertEquals(expected.getRecommended().size(), actual.getRecommended().size());
        verify(playlistRepositoryMock).findByPlaylistId(3L);
    }

    @Test
    void getRecommendations_valid_twoGenres(){
        GetRecommendationsRequest request = GetRecommendationsRequest.builder().userId(2L).build();
        List<Playlist> playlists = new ArrayList<>();
        playlists.add(Playlist.builder().id(1L).userId(2L).title("Christmas").songs(new ArrayList<>()).genres(new ArrayList<>()).build());
        playlists.add(Playlist.builder().id(2L).userId(2L).title("Party").songs(new ArrayList<>()).genres(new ArrayList<>()).build());
        playlists.add(Playlist.builder().id(3L).userId(2L).title("Lazy").songs(new ArrayList<>()).genres(new ArrayList<>()).build());
        List<Long> mostLikedGenres = new ArrayList<>();
        mostLikedGenres.add(2L);
        mostLikedGenres.add(16L);
        List<Long> recommendedPlaylists = new ArrayList<>();
        recommendedPlaylists.add(1L);
        recommendedPlaylists.add(2L);
        recommendedPlaylists.add(3L);
        when(userRepositoryMock.existsById(request.getUserId())).thenReturn(true);
        when(playlistRepositoryMock.findMostLikedGenres(request.getUserId())).thenReturn(mostLikedGenres);
        when(playlistRepositoryMock.findBestMatchingPlaylists(mostLikedGenres.get(0), mostLikedGenres.get(1), mostLikedGenres.get(0), request.getUserId()))
                .thenReturn(recommendedPlaylists);
        when(playlistRepositoryMock.findByPlaylistId(1L)).thenReturn(PlaylistConverter.convertToPlaylistEntity(playlists.get(0)));
        when(playlistRepositoryMock.findByPlaylistId(2L)).thenReturn(PlaylistConverter.convertToPlaylistEntity(playlists.get(1)));
        when(playlistRepositoryMock.findByPlaylistId(3L)).thenReturn(PlaylistConverter.convertToPlaylistEntity(playlists.get(2)));
        GetRecommendationsResponse actual = getRecommendationsUseCase.getRecommendations(request);
        GetRecommendationsResponse expected = GetRecommendationsResponse.builder().recommended(playlists).build();
        assertEquals(expected.getRecommended().size(), actual.getRecommended().size());
        verify(playlistRepositoryMock).findByPlaylistId(3L);
    }

    @Test
    void getRecommendations_onlyOneGenre(){
        GetRecommendationsRequest request = GetRecommendationsRequest.builder().userId(2L).build();
        List<Playlist> playlists = new ArrayList<>();
        playlists.add(Playlist.builder().id(1L).userId(2L).title("Christmas").songs(new ArrayList<>()).genres(new ArrayList<>()).build());
        playlists.add(Playlist.builder().id(2L).userId(2L).title("Party").songs(new ArrayList<>()).genres(new ArrayList<>()).build());
        playlists.add(Playlist.builder().id(3L).userId(2L).title("Lazy").songs(new ArrayList<>()).genres(new ArrayList<>()).build());
        List<Long> mostLikedGenres = new ArrayList<>();
        mostLikedGenres.add(2L);
        List<Long> recommendedPlaylists = new ArrayList<>();
        recommendedPlaylists.add(1L);
        recommendedPlaylists.add(2L);
        recommendedPlaylists.add(3L);
        when(userRepositoryMock.existsById(request.getUserId())).thenReturn(true);
        when(playlistRepositoryMock.findMostLikedGenres(request.getUserId())).thenReturn(mostLikedGenres);
        when(playlistRepositoryMock.findBestMatchingPlaylists(mostLikedGenres.get(0), mostLikedGenres.get(0), mostLikedGenres.get(0), request.getUserId()))
                .thenReturn(recommendedPlaylists);
        when(playlistRepositoryMock.findByPlaylistId(1L)).thenReturn(PlaylistConverter.convertToPlaylistEntity(playlists.get(0)));
        when(playlistRepositoryMock.findByPlaylistId(2L)).thenReturn(PlaylistConverter.convertToPlaylistEntity(playlists.get(1)));
        when(playlistRepositoryMock.findByPlaylistId(3L)).thenReturn(PlaylistConverter.convertToPlaylistEntity(playlists.get(2)));
        GetRecommendationsResponse actual = getRecommendationsUseCase.getRecommendations(request);
        GetRecommendationsResponse expected = GetRecommendationsResponse.builder().recommended(playlists).build();
        assertEquals(expected.getRecommended().size(), actual.getRecommended().size());
        verify(playlistRepositoryMock).findByPlaylistId(3L);
    }

    @Test
    void getRecommendations_noGenres(){
//        GetRecommendationsRequest request = GetRecommendationsRequest.builder().userId(2L).build();
//        List<Playlist> playlists = new ArrayList<>();
//        playlists.add(Playlist.builder().id(1L).userId(2L).title("Christmas").songs(new ArrayList<>()).genres(new ArrayList<>()).build());
//        playlists.add(Playlist.builder().id(2L).userId(2L).title("Party").songs(new ArrayList<>()).genres(new ArrayList<>()).build());
//        playlists.add(Playlist.builder().id(3L).userId(2L).title("Lazy").songs(new ArrayList<>()).genres(new ArrayList<>()).build());
//        List<Long> mostLikedGenres = new ArrayList<>();
//        List<Long> recommendedPlaylists = new ArrayList<>();
//        when(userRepositoryMock.existsById(request.getUserId())).thenReturn(true);
//        when(playlistRepositoryMock.findMostLikedGenres(request.getUserId())).thenReturn(mostLikedGenres);
//        when(playlistRepositoryMock.findBestMatchingPlaylists(mostLikedGenres.get(0), mostLikedGenres.get(0), mostLikedGenres.get(0), request.getUserId()))
//                .thenThrow(IndexOutOfBoundsException.class);
//        when(playlistRepositoryMock.findByPlaylistId(1L)).thenReturn(PlaylistConverter.convertToPlaylistEntity(playlists.get(0)));
//        when(playlistRepositoryMock.findByPlaylistId(2L)).thenReturn(PlaylistConverter.convertToPlaylistEntity(playlists.get(1)));
//        when(playlistRepositoryMock.findByPlaylistId(3L)).thenReturn(PlaylistConverter.convertToPlaylistEntity(playlists.get(2)));
//        assertThrows(IndexOutOfBoundsException.class, () -> getRecommendationsUseCase.getRecommendations(request));
    }

    @Test
    void getRecommendations_userDoesNotExist(){
        GetRecommendationsRequest request = GetRecommendationsRequest.builder().userId(2L).build();
        assertThrows(UserNotFoundException.class, () -> getRecommendationsUseCase.getRecommendations(request));
    }

    @Test
    void getRecommendations_userIsNull(){
        GetRecommendationsRequest request = GetRecommendationsRequest.builder().userId(null).build();
        assertThrows(UserNotFoundException.class, () -> getRecommendationsUseCase.getRecommendations(request));
    }

}