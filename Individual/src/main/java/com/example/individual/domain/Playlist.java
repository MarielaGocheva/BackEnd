package com.example.individual.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Builder
@Getter
@AllArgsConstructor
public class Playlist {
    private Long id;
    private Long userId;
    private String title;
    private Double duration;
    private String imageUrl;
    private Integer plays;
    private List<Song> songs;
    private List<Genre> genres;
}
