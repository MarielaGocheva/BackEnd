package com.example.individual.repository;

import com.example.individual.repository.entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Long> {
    boolean existsById(Long id);
    PlaylistEntity findByPlaylistId(Long id);
    PlaylistEntity findByTitleAndUserId(String title, Long id);
    List<PlaylistEntity> findAllByUserId(Long id);
    List<PlaylistEntity> findByTitleContainsIgnoreCase(String searchItem);
    Boolean existsByTitleAndUserId(String title, Long userId);
}
