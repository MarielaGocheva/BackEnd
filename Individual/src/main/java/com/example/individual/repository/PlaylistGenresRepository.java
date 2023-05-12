package com.example.individual.repository;

import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.PlaylistGenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistGenresRepository extends JpaRepository<PlaylistGenreEntity, Long> {
    List<PlaylistGenreEntity> findAllByPlaylist(PlaylistEntity playlist);
}
