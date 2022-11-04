package com.example.individual.repository;

import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.SongEntity;

import java.util.List;
import java.util.Optional;

//try
public interface PlaylistRepository {
    boolean existsById(Long id);
    Optional<PlaylistEntity> findById(Long id);

    PlaylistEntity save(PlaylistEntity playlist);

    int count();
    List<PlaylistEntity> findAllByUserId(long id);
    List<PlaylistEntity> findAll();
    List<SongEntity> getSongs(long id);
}
