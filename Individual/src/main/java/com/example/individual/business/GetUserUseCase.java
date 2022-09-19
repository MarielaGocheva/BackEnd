package com.example.individual.business;

import com.example.individual.domain.User;

import java.util.Optional;

public interface GetUserUseCase {
    Optional<User> getUser(long studentId);
}
