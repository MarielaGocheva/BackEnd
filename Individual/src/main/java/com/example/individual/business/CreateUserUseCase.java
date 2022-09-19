package com.example.individual.business;

import com.example.individual.domain.CreateUserRequest;
import com.example.individual.domain.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest request);
}
