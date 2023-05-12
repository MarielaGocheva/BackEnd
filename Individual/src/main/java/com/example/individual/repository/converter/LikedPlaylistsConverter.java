package com.example.individual.business.converter;

import com.example.individual.domain.LikedPlaylist;
import com.example.individual.repository.entity.LikedPlaylistsEntity;

public final class LikedPlaylistsConverter {
    public LikedPlaylistsConverter(){
        //Constructor
    }

    public static LikedPlaylist ConvertToLikedPlaylist(LikedPlaylistsEntity likedPlaylist){
        return LikedPlaylist.builder()
                .id(likedPlaylist.getId())
                .playlist(PlaylistConverter.convertToPlaylist(likedPlaylist.getPlaylist()))
                .user(UserConverter.convertToUser(likedPlaylist.getUser()))
                .build();
    }

    public static LikedPlaylistsEntity ConvertToLikedPlaylistEntity(LikedPlaylist likedPlaylist){
        return LikedPlaylistsEntity.builder()
                .id(likedPlaylist.getId())
                .playlist(PlaylistConverter.convertToPlaylistEntity(likedPlaylist.getPlaylist()))
                .user(UserConverter.convertToUserEntity(likedPlaylist.getUser()))
                .build();
    }
}
