package com.example.individual.repository;

import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.SongEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PlaylistRepositoryImpl implements PlaylistRepository{
    private static long NEXT_ID = 1;
    private final List<PlaylistEntity> savedPlaylists;

    public PlaylistRepositoryImpl() {
        this.savedPlaylists = new ArrayList<>();
    }


    @Override
    public Optional<PlaylistEntity> findById(Long playlistId) {
        return this.savedPlaylists.stream()
                .filter(playlistEntity -> playlistEntity.getId().equals(playlistId))
                .findFirst();
    }

    @Override
    public PlaylistEntity save(PlaylistEntity playlist) {
        playlist.setId(NEXT_ID);
        NEXT_ID++;
        this.savedPlaylists.add(playlist);
        return playlist;
    }

    @Override
    public boolean existsById(Long id) {
        for (PlaylistEntity playlist : this.savedPlaylists
        ) {
            if (playlist.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int count() {
        return this.savedPlaylists.size();
    }

    @Override
    public List<PlaylistEntity> findAllByUserId(long id) {
        List<PlaylistEntity> results = new ArrayList<>();
        for (PlaylistEntity pl : savedPlaylists
             )
        {
            if(pl.getUserId() == id)   {
                results.add(pl);
            }
        }
        return results;
    }

    @Override
    public List<PlaylistEntity> findAll() {
        return savedPlaylists;
    }

    @Override
    public List<SongEntity> getSongs(long id)
    {
        List<SongEntity> songs = new ArrayList<>();
        for (PlaylistEntity pl : savedPlaylists){
            if(pl.getId() == id){
                songs = pl.getSongs();
            }
        }
        return songs;
    };
}
