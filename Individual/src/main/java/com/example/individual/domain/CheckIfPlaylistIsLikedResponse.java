package com.example.individual.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckIfPlaylistIsLikedResponse {
    private Boolean liked;
}
