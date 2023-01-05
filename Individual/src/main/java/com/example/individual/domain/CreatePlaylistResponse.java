package com.example.individual.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePlaylistResponse {
    private Playlist playlist;
}
