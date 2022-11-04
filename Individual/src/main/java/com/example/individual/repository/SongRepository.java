package com.example.individual.repository;

import com.example.individual.repository.entity.SongEntity;

public interface SongRepository {
    SongEntity add(long playlistId, String songUri);
    Boolean alreadyAdded(long playlistId, String songUri);
}
