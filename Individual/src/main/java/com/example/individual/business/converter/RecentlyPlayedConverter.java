package com.example.individual.business.converter;

import com.example.individual.domain.RecentlyPlayed;
import com.example.individual.repository.entity.RecentlyPlayedEntity;

public final class RecentlyPlayedConverter {
    private RecentlyPlayedConverter(){
        //Constructor
    }

    public static RecentlyPlayed convertToRecentlyPlayed(RecentlyPlayedEntity recentlyPlayed){
        return RecentlyPlayed.builder()
                .id(recentlyPlayed.getId())
                .userId(recentlyPlayed.getUserId())
                .playlistId(recentlyPlayed.getPlaylistId())
                .songId(recentlyPlayed.getSongId())
                .build();
    }
    public static RecentlyPlayedEntity convertToRecentlyPlayedEntity(RecentlyPlayed recentlyPlayed){
        return RecentlyPlayedEntity.builder()
                .id(recentlyPlayed.getId())
                .userId(recentlyPlayed.getUserId())
                .playlistId(recentlyPlayed.getPlaylistId())
                .songId(recentlyPlayed.getSongId())
                .build();
    }
}
