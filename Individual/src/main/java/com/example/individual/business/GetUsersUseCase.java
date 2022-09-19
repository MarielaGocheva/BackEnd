package com.example.individual.business;

import com.example.individual.domain.GetAllUsersRequest;
import com.example.individual.domain.GetAllUsersResponse;

public interface GetUsersUseCase {
    GetAllUsersResponse getUsers(GetAllUsersRequest request);
}
