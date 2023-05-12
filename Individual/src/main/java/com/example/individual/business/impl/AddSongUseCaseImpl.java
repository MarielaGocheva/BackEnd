package com.example.individual.business.impl;

import com.example.individual.business.AddSongUseCase;
import com.example.individual.business.exceptions.PlaylistDoesNotExist;
import com.example.individual.domain.AddSongRequest;
import com.example.individual.domain.AddSongResponse;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.PlaylistSongRepository;
import com.example.individual.repository.SongRepository;
import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.PlaylistSongEntity;
import com.example.individual.repository.entity.SongEntity;
import lombok.AllArgsConstructor;
import org.apache.el.stream.Optional;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddSongUseCaseImpl implements AddSongUseCase {
    private PlaylistRepository playlistRepository;
    private SongRepository songRepository;
    private PlaylistSongRepository playlistSongRepository;

    @Override
    public AddSongResponse addSong(AddSongRequest request) {
        if (!playlistRepository.existsById(request.getPlaylistId())) {
            throw new PlaylistDoesNotExist();
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
        try {
            SongEntity newSong = SongEntity.builder().songUri(request.getSongUri()).artist(request.getArtist()).imageUrl(request.getImg()).title(request.getTitle()).build();
            songRepository.save(newSong);
            PlaylistEntity playlist = playlistRepository.findById(request.getPlaylistId()).orElseThrow();
            PlaylistSongEntity pl = PlaylistSongEntity.builder().playlist(playlist).song(newSong).build();
            playlistSongRepository.save(pl);
            return newSong;
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }
}
