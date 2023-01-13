package com.example.individual.repository;

import com.example.individual.repository.entity.LikedPlaylistsEntity;
import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface LikedPlaylistsRepository extends JpaRepository<LikedPlaylistsEntity, Long> {
    @Transactional
    void deleteByUserAndPlaylist(UserEntity user, PlaylistEntity playlist);
    Boolean existsByUserAndPlaylist(UserEntity user, PlaylistEntity playlist);
    List<LikedPlaylistsEntity> findAllByUser(UserEntity user);
}
