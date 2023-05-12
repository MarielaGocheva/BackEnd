package com.example.individual.business.impl;

import com.example.individual.business.CreatePlaylistUseCase;
import com.example.individual.business.converter.GenreConverter;
import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.business.exceptions.GenresDoNotExist;
import com.example.individual.business.exceptions.PlaylistDoesNotExist;
import com.example.individual.domain.*;
import com.example.individual.repository.GenreRepository;
import com.example.individual.repository.PlaylistGenresRepository;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.entity.PlaylistGenreEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        if(!playlistRepository.existsByTitleAndUserId(request.getName(), request.getUserId())){
            List<Song> songs = new ArrayList<>();
            List<String> genreIds = Arrays.asList(request.getGenres().split(","));
            List<Genre> genres = new ArrayList<>();
            try {
                 genres = genreIds.stream().map(genre -> GenreConverter.convertToGenre(genreRepository.getReferenceById(Long.parseLong(genre)))).toList();
            }
            catch (GenresDoNotExist e){
                throw new GenresDoNotExist();
            }
            //Setting default playlist picture
            Playlist playlist = Playlist.builder().userId(request.getUserId()).title(request.getName()).songs(songs).duration(0D).imageUrl("https://static.wixstatic.com/media/66187e_804b20c212cb41358c5fe6053c15bc55~mv2.png").build();
            Playlist savedPlaylist = PlaylistConverter.convertToPlaylist(playlistRepository.save(PlaylistConverter.convertToPlaylistEntity(playlist)));
            for (Genre genre : genres) {
                playlistGenresRepository.save(PlaylistGenreEntity.builder().genre(GenreConverter.convertToGenreEntity(genre)).playlist(PlaylistConverter.convertToPlaylistEntity(savedPlaylist)).build());
            }
            return CreatePlaylistResponse.builder()
                    .playlist(savedPlaylist)
                    .build();
        }
        else {
            throw new PlaylistDoesNotExist();
        }

    }
}
