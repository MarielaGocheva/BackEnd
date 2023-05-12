package com.example.individual.repository;

import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface SongRepository extends JpaRepository<SongEntity, Long> {
//    SongEntity add(long playlistId, String songUri);
//    SongEntity save(long playlistId,SongEntity song);
//    Boolean alreadyAdded(long playlistId, String songUri);
//    List<SongEntity> findAllByPlaylistId(long id);
    SongEntity findBySongUri(String uri);
    SongEntity findBySongId(Long songId);
    Boolean existsBySongUri(String songUri);
    List<SongEntity> findTop20ByOrderByPlaysDesc();
    @Modifying
    @Transactional
    @Query("UPDATE SongEntity s SET s.plays = s.plays +1 WHERE s.songUri = :songUri")
    void updatePlays(@Param("songUri") String songUri);
}
