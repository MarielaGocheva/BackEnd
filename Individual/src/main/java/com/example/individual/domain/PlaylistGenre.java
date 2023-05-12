package com.example.individual.domain;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
public class PlaylistGenre {
    private int id;
    private Playlist playlist;
    private Genre genre;
}
