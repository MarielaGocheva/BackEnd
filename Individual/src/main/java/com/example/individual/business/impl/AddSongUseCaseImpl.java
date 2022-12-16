package com.example.individual.business.impl;

import com.example.individual.business.AddSongUseCase;
import com.example.individual.domain.*;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.SongRepository;
import com.example.individual.repository.entity.SongEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddSongUseCaseImpl implements AddSongUseCase {
    private PlaylistRepository playlistRepository;
    private SongRepository songRepository;

    @Override
    public AddSongResponse addSong(AddSongRequest request) {
        if (!playlistRepository.existsById(request.getPlaylistId())) {
            //throw exception playlist doesn't exist
            return AddSongResponse.builder()
                    .added(false)
                    .build();
        } else {
            try {
                SongEntity addedSong = addNewSong(request);
                return AddSongResponse.builder()
                        .added(true)
                        .build();
            }
            catch (Exception e){
                return AddSongResponse.builder()
                        .added(false)
                        .build();
            }
        }
    }

    private SongEntity addNewSong(AddSongRequest request) {
        return songRepository.add(request.getPlaylistId(), request.getSongUri());
    }
}
