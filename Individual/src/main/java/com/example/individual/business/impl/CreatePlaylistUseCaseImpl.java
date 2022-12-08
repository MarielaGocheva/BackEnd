package com.example.individual.business.impl;

import com.example.individual.business.CreatePlaylistUseCase;
import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.domain.CreatePlaylistRequest;
import com.example.individual.domain.CreatePlaylistResponse;
import com.example.individual.domain.Playlist;
import com.example.individual.domain.Song;
import com.example.individual.repository.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CreatePlaylistUseCaseImpl implements CreatePlaylistUseCase {
    private PlaylistRepository playlistRepository;

    @Override
    public CreatePlaylistResponse createPlaylist(CreatePlaylistRequest request){
        //Check if it exists by name first
//        if(playlistRepository.existsByName(request.getName())){
//            throw new RuntimeException();
//        }
        List<Song> songs = new ArrayList<>();
        Playlist playlist = Playlist.builder().userId(request.getUserId()).title(request.getName()).songs(songs).duration(0D).build();
       Playlist savedPlaylist = PlaylistConverter.convertToPlaylist(playlistRepository.save(PlaylistConverter.convertToPlaylistEntity(playlist)));
        return CreatePlaylistResponse.builder()
                .playlist(savedPlaylist)
                .build();
    }
}
