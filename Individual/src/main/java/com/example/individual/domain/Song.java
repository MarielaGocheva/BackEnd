package com.example.individual.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    private Long id;
    private String songUri;
    private String artist;
    private Double duration;
    private String imageUrl;
}
