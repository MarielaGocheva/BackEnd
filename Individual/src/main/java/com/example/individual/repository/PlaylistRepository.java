package com.example.individual.repository;

import com.example.individual.repository.entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Long> {
    boolean existsByPlaylistId(Long id);
    PlaylistEntity findByPlaylistId(Long id);
    PlaylistEntity findByTitleAndUserId(String title, Long id);
    List<PlaylistEntity> findAllByUserId(Long id);
    List<PlaylistEntity> findByTitleContainsIgnoreCase(String searchItem);
    Boolean existsByTitleAndUserId(String title, Long userId);
    List<PlaylistEntity> findTop20ByOrderByPlaysDesc();
    @Modifying
    @Transactional
    @Query("UPDATE PlaylistEntity p SET p.plays = p.plays +1 WHERE p.playlistId = :playlistId")
    void updatePlays(@Param("playlistId") Long playlistId);

//    @Query(value = "SELECT COUNT(*) FROM playlist AS p INNER JOIN playlist_genres pg on p.playlist_id = pg.playlist_id WHERE user_id = :userId GROUP BY genre_id",
//    nativeQuery = true)
@Query(value = "SELECT genre_id, COUNT(genre_id) AS c FROM playlist_genres AS pg INNER JOIN playlist p on pg.playlist_id = p.playlist_id INNER JOIN liked_playlists lp on p.playlist_id = lp.playlist_id " +
        "WHERE lp.user_id = :userId GROUP BY genre_id ORDER BY c DESC LIMIT 3",
        nativeQuery = true)
    List<Long> findMostLikedGenres (@Param("userId") Long userId);

@Query(value="SELECT DISTINCT p.playlist_id FROM playlist AS p INNER JOIN playlist_genres pg on p.playlist_id = pg.playlist_id WHERE " +
        "(genre_id = :genreId1 OR genre_id = :genreId2 OR genre_id = :genreId3) AND p.playlist_id NOT IN (SELECT playlist_id FROM liked_playlists WHERE " +
        "liked_playlists.user_id = :userId) ORDER BY plays DESC",
nativeQuery = true)
    List<Long> findBestMatchingPlaylists (@Param ("genreId1") Long genreId1, @Param("genreId2") Long genreId2, @Param("genreId3") Long genreId3, @Param("userId") Long userId);
}
