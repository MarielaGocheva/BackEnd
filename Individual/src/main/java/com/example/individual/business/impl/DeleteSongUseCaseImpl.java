package com.example.individual.business.impl;

import com.example.individual.business.DeleteSongUseCase;
import com.example.individual.business.exceptions.PlaylistDoesNotExist;
import com.example.individual.business.exceptions.SongDoesNotExist;
import com.example.individual.domain.DeleteSongRequest;
import com.example.individual.domain.DeleteSongResponse;
import com.example.individual.repository.PlaylistSongRepository;
import com.example.individual.repository.SongRepository;
import com.example.individual.repository.entity.SongEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteSongUseCaseImpl implements DeleteSongUseCase {
    private PlaylistSongRepository playlistSongRepository;
    private SongRepository songRepository;

    @Override
    public DeleteSongResponse deleteSong(DeleteSongRequest request){
        if(request.getSongUri().isEmpty() || songRepository.findBySongUri(request.getSongUri()) == null){
            throw new SongDoesNotExist();
        }
        if(request.getPlaylistId()==null || playlistSongRepository.existsByPlaylist(request.getPlaylistId())){
            throw new PlaylistDoesNotExist();
        }

        try{
            SongEntity song = songRepository.findBySongUri(request.getSongUri());
            playlistSongRepository.deleteByPlaylistAndSong(request.getPlaylistId(), song.getSongId());
            songRepository.delete(song);
            return DeleteSongResponse.builder().deleted(true).build();
        }
        catch (Exception e){
            return DeleteSongResponse.builder().deleted(false).build();
        }
    }
}
