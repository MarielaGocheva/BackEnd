package com.example.individual.repository;

import com.example.individual.repository.entity.RecentlyPlayedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecentlyPlayedRepository extends JpaRepository<RecentlyPlayedEntity, Long> {
    RecentlyPlayedEntity findTop1ByUserIdOrderByIdDesc(Long userId);
    List<RecentlyPlayedEntity> findAllByUserIdOrderByIdDesc(Long userId);
}
