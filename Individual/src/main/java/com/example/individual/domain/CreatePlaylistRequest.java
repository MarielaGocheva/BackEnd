package com.example.individual.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePlaylistRequest {
    private Long userId;
    private String name;
    private String genres;
}
