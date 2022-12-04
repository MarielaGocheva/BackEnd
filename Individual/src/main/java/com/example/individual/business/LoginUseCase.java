package com.example.individual.business;

import com.example.individual.domain.LoginRequest;
import com.example.individual.domain.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest loginRequest);
}
