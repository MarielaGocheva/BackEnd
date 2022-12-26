package com.example.individual.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddSongRequest {
    private Long playlistId;
    private String songUri;
    private String artist;
    private String title;
    private String img;
}
