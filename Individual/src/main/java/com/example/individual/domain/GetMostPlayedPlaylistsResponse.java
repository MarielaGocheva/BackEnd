package com.example.individual.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class GetMostPlayedPlaylistsResponse {
    private List<Playlist> mostPlayed;
}
