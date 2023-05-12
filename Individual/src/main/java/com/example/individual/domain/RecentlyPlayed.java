package com.example.individual.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class RecentlyPlayed {
    private Long id;
    private Long userId;
    private Long playlistId;
    private Long songId;
}
