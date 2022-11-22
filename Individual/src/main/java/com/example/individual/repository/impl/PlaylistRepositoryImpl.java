package com.example.individual.repository.impl;

import com.example.individual.domain.Playlist;
import com.example.individual.domain.Song;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.converter.PlaylistConverter;
import com.example.individual.repository.converter.SongConverter;
import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.SongEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PlaylistRepositoryImpl implements PlaylistRepository {
    private static long NEXT_ID = 1;
    private final List<PlaylistEntity> savedPlaylists;
    private PlaylistConverter playlistConverter;

    public PlaylistRepositoryImpl() {

        this.savedPlaylists = new ArrayList<>();
        this.playlistConverter = new PlaylistConverter(new SongConverter());
    }


    @Override
    public Optional<PlaylistEntity> findById(Long playlistId) {
        return this.savedPlaylists.stream()
                .filter(playlistEntity -> playlistEntity.getId().equals(playlistId))
                .findFirst();
    }

    @Override
    public List<Playlist> save(Playlist playlist) {
        playlist.setId(NEXT_ID);
        NEXT_ID++;
        this.savedPlaylists.add(playlistConverter.convertToPlaylistEntity(playlist));
        List<Playlist> results = new ArrayList<>();
        for (PlaylistEntity playlistEntity : savedPlaylists) {
            results.add(playlistConverter.convertToPlaylist(playlistEntity));
        }
        return results;
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
    public List<Playlist> findAllByUserId(long id) {
        List<Playlist> results = new ArrayList<>();
        for (PlaylistEntity pl : savedPlaylists)
        {
            if(pl.getUserId() == id)   {
                results.add(playlistConverter.convertToPlaylist(pl));
            }
        }
        return results;
    }

    @Override
    public List<Playlist> findAll() {
        List<Playlist> playlists = new ArrayList<>();
        for (PlaylistEntity pl : savedPlaylists){
            playlists.add(playlistConverter.convertToPlaylist(pl));
        }
        return playlists;
    }

    @Override
    public List<Song> getSongs(long id)
    {
        Playlist playlist = new Playlist();
        for (PlaylistEntity pl : savedPlaylists){
            if(pl.getId() == id){
               playlist = playlistConverter.convertToPlaylist(pl);
            }
        }
        return playlist.getSongs();
    };
}
