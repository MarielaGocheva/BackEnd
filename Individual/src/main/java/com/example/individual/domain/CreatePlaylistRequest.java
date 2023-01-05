package com.example.individual.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePlaylistRequest {
    private long userId;
    private String name;
    private String genres;
}
