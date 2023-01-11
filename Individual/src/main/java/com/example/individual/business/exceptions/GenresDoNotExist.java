package com.example.individual.business.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GenresDoNotExist extends ResponseStatusException {
    public GenresDoNotExist() {
        super(HttpStatus.BAD_REQUEST, "GENRES_DO_NOT_EXIST");
    }
}
