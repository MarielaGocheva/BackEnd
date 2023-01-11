package com.example.individual.business.impl;

import com.example.individual.business.converter.GenreConverter;
import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.business.converter.SongConverter;
import com.example.individual.business.exceptions.GenresDoNotExist;
import com.example.individual.business.exceptions.PlaylistDoesNotExist;
import com.example.individual.domain.*;
import com.example.individual.repository.GenreRepository;
import com.example.individual.repository.PlaylistGenresRepository;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePlaylistUseCaseImplTest {
    @Mock
    PlaylistRepository playlistRepositoryMock;
    @Mock
    GenreRepository genreRepositoryMock;
    @Mock
    PlaylistGenresRepository playlistGenresRepository;
    @InjectMocks
    private CreatePlaylistUseCaseImpl createPlaylistUseCase;

    @Test
    void createPlaylist_doesNotExistById(){
        CreatePlaylistRequest request = CreatePlaylistRequest.builder().userId(1L).name("Christmas Songs").genres("1,5").build();
        List<Song> songs = new ArrayList<>();
        GenreEntity pop = GenreEntity.builder().id(1L).name("Pop").build();
        GenreEntity rock = GenreEntity.builder().id(5L).name("Rock").build();
        when(genreRepositoryMock.getReferenceById(1L)).thenReturn(pop);
        when(genreRepositoryMock.getReferenceById(5L)).thenReturn(rock);
        List<String> genreIds = Arrays.asList(request.getGenres().split(","));
        List<Genre> genres = genreIds.stream().map(genre -> GenreConverter.convertToGenre(genreRepositoryMock.getReferenceById(Long.parseLong(genre)))).toList();
        Playlist playlist = Playlist.builder().userId(1L).title("Christmas Songs").duration(0.0).imageUrl("https://static.wixstatic.com/media/66187e_804b20c212cb41358c5fe6053c15bc55~mv2.png").songs(songs).genres(genres).build();
        PlaylistEntity playlistEntity = PlaylistConverter.convertToPlaylistEntity(playlist);
        when(playlistRepositoryMock.save(playlistEntity)).thenReturn(playlistEntity);
        CreatePlaylistResponse actual = createPlaylistUseCase.createPlaylist(request);
        CreatePlaylistResponse expected = CreatePlaylistResponse.builder().playlist(playlist).build();
        assertEquals(expected.getPlaylist().getTitle(), actual.getPlaylist().getTitle());
        verify(playlistRepositoryMock).save(playlistEntity);
    }

    @Test
    void createPlaylist_existByTitleAndUserId(){
        CreatePlaylistRequest request = CreatePlaylistRequest.builder().userId(1L).name("Christmas Songs").genres("1,5").build();
        when(playlistRepositoryMock.existsByTitleAndUserId(request.getName(), request.getUserId())).thenThrow(PlaylistDoesNotExist.class);
        assertThrows(PlaylistDoesNotExist.class, () -> createPlaylistUseCase.createPlaylist(request));
    }

    @Test
    void createPlaylist_withNonexistentGenres(){
        CreatePlaylistRequest request = CreatePlaylistRequest.builder().userId(1L).name("Christmas Songs").genres("0,7").build();
        when(playlistRepositoryMock.existsByTitleAndUserId(request.getName(), request.getUserId())).thenReturn(false);
        when(genreRepositoryMock.getReferenceById(0L)).thenThrow(GenresDoNotExist.class);
        assertThrows(GenresDoNotExist.class, () -> createPlaylistUseCase.createPlaylist(request));
    }

    @Test
    void savePlaylistGenre_valid(){
        List<SongEntity> songs = new ArrayList<>();
        List<GenreEntity> genres = new ArrayList<>();
        GenreEntity rock = GenreEntity.builder().id(5L).name("Rock").build();
        genres.add(rock);
        PlaylistEntity playlistEntity = PlaylistEntity.builder().userId(1L).title("Christmas Songs").duration(0.0)
                .imageUrl("https://static.wixstatic.com/media/66187e_804b20c212cb41358c5fe6053c15bc55~mv2.png").songs(songs).genres(genres).build();
        PlaylistGenreEntity rockPlaylistRelation = PlaylistGenreEntity.builder().playlist(playlistEntity).genre(rock).build();
        when(playlistGenresRepository.save(rockPlaylistRelation)).thenReturn(rockPlaylistRelation);
        PlaylistGenreEntity actual = playlistGenresRepository.save(rockPlaylistRelation);
        PlaylistGenreEntity expected = PlaylistGenreEntity.builder().playlist(playlistEntity).genre(rock).build();
        assertEquals(expected.getPlaylist().getTitle(), actual.getPlaylist().getTitle());
        verify(playlistGenresRepository).save(rockPlaylistRelation);
    }
}