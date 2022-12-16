package com.example.individual.repository;

import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Long> {
    boolean existsById(Long id);
    PlaylistEntity findByPlaylistId(Long id);

//    List<PlaylistEntity> save(PlaylistEntity playlist);

//    int count();
    List<PlaylistEntity> findAllByUserId(long id);
//    List<Playlist> findAll();
    List<SongEntity> findSongsByPlaylistId(long playlistId);
}
