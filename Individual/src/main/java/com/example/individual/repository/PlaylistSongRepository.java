package com.example.individual.repository;

import com.example.individual.repository.entity.PlaylistSongEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistSongRepository extends JpaRepository<PlaylistSongEntity, Long> {
    void deleteByPlaylistAndSong(Long playlistId, Long songId);
    Boolean existsByPlaylist(Long playlistId);
}
