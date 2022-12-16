package com.example.individual.business;

import com.example.individual.domain.User;


public interface GetUserUseCase {
    User getUser(long studentId);
}
