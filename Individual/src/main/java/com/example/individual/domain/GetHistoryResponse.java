package com.example.individual.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
public class GetHistoryResponse {
    @Getter
    private List<Song> recentlyPlayed;
}
