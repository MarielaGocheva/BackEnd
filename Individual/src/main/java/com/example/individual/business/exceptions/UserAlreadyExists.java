package com.example.individual.business.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExists extends ResponseStatusException {
    public UserAlreadyExists() {
        super(HttpStatus.BAD_REQUEST, "USER_WITH_THIS_EMAIL_ALREADY_EXISTS");
    }
}
