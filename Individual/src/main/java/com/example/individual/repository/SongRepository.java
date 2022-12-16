package com.example.individual.repository;

import com.example.individual.repository.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<SongEntity, Long> {
//    SongEntity add(long playlistId, String songUri);
//    SongEntity save(long playlistId,SongEntity song);
//    Boolean alreadyAdded(long playlistId, String songUri);
//    List<SongEntity> findAllByPlaylistId(long id);
    SongEntity findBySongUri(String uri);
}
