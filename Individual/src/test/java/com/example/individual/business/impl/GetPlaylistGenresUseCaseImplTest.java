package com.example.individual.business.impl;

import com.example.individual.business.converter.GenreConverter;
import com.example.individual.domain.Genre;
import com.example.individual.domain.GetPlaylistGenresRequest;
import com.example.individual.domain.GetPlaylistGenresResponse;
import com.example.individual.domain.PlaylistGenre;
import com.example.individual.repository.GenreRepository;
import com.example.individual.repository.PlaylistGenresRepository;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.entity.GenreEntity;
import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.PlaylistGenreEntity;
import org.junit.jupiter.api.BeforeEach;
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
class GetPlaylistGenresUseCaseImplTest {
    @Mock
    private PlaylistGenresRepository playlistGenresRepositoryMock;
    @Mock
    private PlaylistRepository playlistRepositoryMock;
    @Mock
    private GenreRepository genreRepositoryMock;
    @InjectMocks
    private GetPlaylistGenresUseCaseImpl getPlaylistGenresUseCase;

    @Test
    void getPlaylistGenres_valid(){
//        Genre genre = Genre.builder().id(2L).name("Pop").build();
//        Genre genre2 = Genre.builder().id(3L).name("Pop").build();
//        Genre genre3 = Genre.builder().id(4L).name("Pop").build();
//        GenreEntity genreEntity = GenreConverter.convertToGenreEntity(genre);
//        GenreEntity genreEntity2 = GenreConverter.convertToGenreEntity(genre);
//        GenreEntity genreEntity3 = GenreConverter.convertToGenreEntity(genre);
//        List<GenreEntity> genreEntities = new ArrayList<>();
//        genreEntities.add(genreEntity);
//        genreEntities.add(genreEntity2);
//        genreEntities.add(genreEntity3);
//        List<Genre> genres = genreEntities.stream().map(GenreConverter::convertToGenre).toList();
//        List<PlaylistGenreEntity> playlistGenres = new ArrayList<>();
//        playlistGenres.add(PlaylistGenreEntity.builder().playlist(PlaylistEntity.builder().userId(1L).playlistId(4L).build()).genre(genreEntity).build());
//        playlistGenres.add(PlaylistGenreEntity.builder().playlist(PlaylistEntity.builder().userId(1L).playlistId(4L).build()).genre(genreEntity2).build());
//        playlistGenres.add(PlaylistGenreEntity.builder().playlist(PlaylistEntity.builder().userId(1L).playlistId(4L).build()).genre(genreEntity3).build());
//        GetPlaylistGenresRequest request = GetPlaylistGenresRequest.builder().playlistId(4L).build();
//        PlaylistEntity playlistEntity = PlaylistEntity.builder().userId(1L).playlistId(4L).build();
//        when(playlistRepositoryMock.existsById(request.getPlaylistId())).thenReturn(true);
//        when(playlistRepositoryMock.findByPlaylistId(request.getPlaylistId())).thenReturn(playlistEntity);
//        when(playlistGenresRepositoryMock.findAllByPlaylist(playlistRepositoryMock.findByPlaylistId(request.getPlaylistId()))).thenReturn(playlistGenres);
//        when(genreRepositoryMock.findByGenreId(genre.getId())).thenReturn(genreEntity);
//        when(genreRepositoryMock.findByGenreId(genre2.getId())).thenReturn(genreEntity2);
//        when(genreRepositoryMock.findByGenreId(genre3.getId())).thenReturn(genreEntity3);
//        GetPlaylistGenresResponse actual = getPlaylistGenresUseCase.getGenres(request);
//        GetPlaylistGenresResponse expected = GetPlaylistGenresResponse.builder().genres(genres).build();
//        assertEquals(expected.getGenres().size(), actual.getGenres().size());

    }
}