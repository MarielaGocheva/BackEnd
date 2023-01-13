package com.example.individual.business.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InsufficientNumberOfLikedPlaylists extends ResponseStatusException {
    public InsufficientNumberOfLikedPlaylists() {
        super(HttpStatus.BAD_REQUEST, "InsufficientNumberOfLikedPlaylists");
    }

}
