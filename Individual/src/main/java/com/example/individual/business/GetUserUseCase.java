package com.example.individual.business;

import com.example.individual.domain.GetUserRequest;
import com.example.individual.domain.GetUserResponse;
import com.example.individual.domain.User;


public interface GetUserUseCase {
    GetUserResponse getUser(GetUserRequest request);
}
