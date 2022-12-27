package com.example.individual.business.impl;

import com.example.individual.business.DeletePlaylistUseCase;
import com.example.individual.business.exceptions.PlaylistDoesNotExist;
import com.example.individual.business.exceptions.SongDoesNotExist;
import com.example.individual.domain.DeletePlaylistRequest;
import com.example.individual.domain.DeletePlaylistResponse;
import com.example.individual.domain.DeleteSongResponse;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.entity.SongEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeletePlaylistUseCaseImpl implements DeletePlaylistUseCase {
    private PlaylistRepository playlistRepository;

    @Override
    public DeletePlaylistResponse deletePlaylist(DeletePlaylistRequest request){
        if(request.getPlaylistId()==null){
            throw new PlaylistDoesNotExist();
        }

        try{
            playlistRepository.deletePlaylistEntityByPlaylistId(request.getPlaylistId());
            return DeletePlaylistResponse.builder().deleted(true).build();
        }
        catch (Exception e){
            return DeletePlaylistResponse.builder().deleted(false).build();
        }
    }
}
