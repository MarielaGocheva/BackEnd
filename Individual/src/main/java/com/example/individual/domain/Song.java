package com.example.individual.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
@AllArgsConstructor
public class Song {
    private Long id;
    private String songUri;
    private String artist;
    private String title;
    private Double duration;
    private String imageUrl;
    private Integer plays;
}
