package com.example.individual.repository;

import com.example.individual.domain.Playlist;
import com.example.individual.domain.Song;
import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.SongEntity;

import java.util.List;
import java.util.Optional;

//try
public interface PlaylistRepository {
    boolean existsById(Long id);
    Optional<PlaylistEntity> findById(Long id);

    Playlist save(Playlist playlist);

    int count();
    List<Playlist> findAllByUserId(long id);
    List<Playlist> findAll();
    List<Song> getSongs(long id);
}
