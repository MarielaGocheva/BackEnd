package com.example.individual.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {
    private Long id;
    private Long userId;
    private Double duration;
    private List<Song> songs;
}
