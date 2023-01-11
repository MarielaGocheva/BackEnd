package com.example.individual.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ArtistUpdate {
    private Playlist playlist;

    public ArtistUpdate() {
    }

    public ArtistUpdate(Playlist content) {
        this.playlist = content;
    }

    public Playlist getContent() {
        return playlist;
    }

}
