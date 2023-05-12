package com.example.individual.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class LikedPlaylist {
    private Long id;
    private Playlist playlist;
    private User user;
}
