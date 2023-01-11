package com.example.individual.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class SetRecentlyPlayedRequest {
    private Long userId;
    private String songUri;
    private String playlistTitle;
}
