package com.example.individual.domain;

import lombok.*;

import java.util.List;


@Builder
@Getter
@AllArgsConstructor
public class Playlist {
    private Long id;
    private Long userId;
    private String title;
    private Double duration;
    private List<Song> songs;
}
