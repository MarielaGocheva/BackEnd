package com.example.individual.business.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SongDoesNotExist extends ResponseStatusException {
    public SongDoesNotExist() {
        super(HttpStatus.BAD_REQUEST, "SONG_DOES_NOT_EXIST");
    }
}
