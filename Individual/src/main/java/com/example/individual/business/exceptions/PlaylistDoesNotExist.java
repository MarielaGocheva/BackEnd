package com.example.individual.business.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PlaylistDoesNotExist extends ResponseStatusException {
    public PlaylistDoesNotExist() {
        super(HttpStatus.BAD_REQUEST, "PLAYLIST_DOES_NOT_EXIST");
    }
}
