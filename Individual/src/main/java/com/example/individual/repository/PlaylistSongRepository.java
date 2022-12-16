package com.example.individual.repository;

import com.example.individual.repository.entity.PlaylistSongEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistSongRepository extends JpaRepository<PlaylistSongEntity, Long> {
//    List<PlaylistSongEntity> findSongsByPlaylistId(long id);
}
