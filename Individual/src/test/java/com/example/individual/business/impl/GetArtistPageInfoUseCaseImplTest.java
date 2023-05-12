package com.example.individual.business.impl;

import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.business.converter.UserConverter;
import com.example.individual.domain.*;
import com.example.individual.domain.enums.Role;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.UserRepository;
import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.UserEntity;
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
class GetArtistPageInfoUseCaseImplTest {
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private PlaylistRepository playlistRepositoryMock;
    @InjectMocks
    private GetArtistPageInfoUseCaseImpl getArtistPageInfoUseCase;

    @Test
    void getArtistInfo_valid(){
        GetArtistPageInfoRequest request = GetArtistPageInfoRequest.builder().id(2L).build();
        List<Song> songs = new ArrayList<>();
        List<Playlist> playlists = new ArrayList<>();
        playlists.add(Playlist.builder().title("Summer").userId(2L).songs(songs).build());
        playlists.add(Playlist.builder().title("Party").userId(2L).songs(songs).build());
        List<PlaylistEntity> playlistEntities = playlists.stream().map(PlaylistConverter::convertToPlaylistEntity).toList();
        User mariela = User.builder().id(2L).fName("Mariela").lName("Gocheva").role(Role.DJ).password("123").build();
        UserEntity marielaEntity = UserConverter.convertToUserEntity(mariela);
        when(userRepositoryMock.findUserById(request.getId())).thenReturn(marielaEntity);
        when(playlistRepositoryMock.findAllByUserId(request.getId())).thenReturn(playlistEntities);
        GetArtistPageInfoResponse actual = getArtistPageInfoUseCase.findArtist(request);
        GetArtistPageInfoResponse expected = GetArtistPageInfoResponse.builder().fName("Mariela").lName("Gocheva").img("shorturl.at/cmAPV").playlists(playlists).build();
        assertEquals(expected.getFName(), actual.getFName());
        verify(playlistRepositoryMock).findAllByUserId(request.getId());
    }
}