package com.example.individual.business.impl;

import com.example.individual.business.CreatePlaylistUseCase;
import com.example.individual.business.converter.GenreConverter;
import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.domain.*;
import com.example.individual.repository.GenreRepository;
import com.example.individual.repository.PlaylistGenresRepository;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.entity.*;
import lombok.AllArgsConstructor;
import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class CreatePlaylistUseCaseImpl implements CreatePlaylistUseCase {
    private PlaylistRepository playlistRepository;
    private GenreRepository genreRepository;
    private PlaylistGenresRepository playlistGenresRepository;

    @Override
    public CreatePlaylistResponse createPlaylist(CreatePlaylistRequest request){
        List<Song> songs = new ArrayList<>();
        List<String> genreIds = Arrays.asList(request.getGenres().split(","));
        List<Genre> genres = genreIds.stream().map(genre -> GenreConverter.convertToGenre(genreRepository.getReferenceById(Long.parseLong(genre)))).toList();
        System.out.println(genres);
        Playlist playlist = Playlist.builder().userId(request.getUserId()).title(request.getName()).songs(songs).duration(0D).build();
        Playlist savedPlaylist = PlaylistConverter.convertToPlaylist(playlistRepository.save(PlaylistConverter.convertToPlaylistEntity(playlist)));
        for (Genre genre : genres) {
            playlistGenresRepository.save(PlaylistGenreEntity.builder().genre(GenreConverter.convertToGenreEntity(genre)).playlist(PlaylistConverter.convertToPlaylistEntity(savedPlaylist)).build());
        }
        return CreatePlaylistResponse.builder()
                .playlist(savedPlaylist)
                .build();
    }
}
