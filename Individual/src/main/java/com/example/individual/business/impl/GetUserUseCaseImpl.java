package com.example.individual.business.impl;

import com.example.individual.business.GetUserUseCase;
import com.example.individual.domain.GetUserRequest;
import com.example.individual.domain.GetUserResponse;
import com.example.individual.domain.User;
import com.example.individual.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {
    private UserRepository userRepository;

    @Override
    public GetUserResponse getUser(GetUserRequest request) {
        User user = userRepository.findById(request.getId());
        return GetUserResponse.builder().user(user).build();
    }
}
