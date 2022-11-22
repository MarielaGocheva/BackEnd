package com.example.individual.repository;

import com.example.individual.domain.Playlist;
import com.example.individual.domain.Song;
import com.example.individual.repository.entity.PlaylistEntity;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository {
    boolean existsById(Long id);
    Optional<PlaylistEntity> findById(Long id);

    List<Playlist> save(Playlist playlist);

    int count();
    List<Playlist> findAllByUserId(long id);
    List<Playlist> findAll();
    List<Song> getSongs(long id);
}
