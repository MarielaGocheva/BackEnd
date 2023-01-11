package com.example.individual.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GetArtistPageInfoResponse {
    private String fName;
    private String lName;
    private String img;
    private List<Playlist> playlists;
}
