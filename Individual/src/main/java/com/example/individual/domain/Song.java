package com.example.individual.domain;


import lombok.*;


@Builder
@Getter
@AllArgsConstructor
public class Song {
    private Long id;
    private String songUri;
    private String artist;
    private Double duration;
    private String imageUrl;
}
