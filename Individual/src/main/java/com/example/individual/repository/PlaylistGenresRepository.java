package com.example.individual.repository;

import com.example.individual.repository.entity.PlaylistGenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistGenresRepository extends JpaRepository<PlaylistGenreEntity, Long> {
}
