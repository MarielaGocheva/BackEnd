package com.example.individual.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Builder
public class CreatePlaylistResponse {
    private Playlist playlist;
}
