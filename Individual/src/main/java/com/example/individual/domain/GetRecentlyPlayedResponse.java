package com.example.individual.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetRecentlyPlayedResponse {
    private Playlist playlist;
    private Song song;
}
