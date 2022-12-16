package com.example.individual.repository.impl;

import com.example.individual.repository.SongRepository;
import com.example.individual.repository.entity.SongEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SongRepositoryImpl implements SongRepository {
    private static long NEXT_ID = 1;
    private final List<SongEntity> addedSongs;

    public SongRepositoryImpl() {
        this.addedSongs = new ArrayList<>();
    }

    @Override
    public SongEntity add(long playlistId, String songUri) {
        try {
            SongEntity added = SongEntity.builder()
                    .songUri(songUri)
                    .build();
            addedSongs.add(added);
            return added;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public Boolean alreadyAdded(long playlistId, String songUri) {
        return this.addedSongs.stream().anyMatch(item -> item.getSongUri().equals(songUri));
    }
}
