package com.example.individual.business.impl;

import com.example.individual.business.GetUsersUseCase;
import com.example.individual.domain.GetAllUsersRequest;
import com.example.individual.domain.GetAllUsersResponse;
import com.example.individual.domain.User;
import com.example.individual.repository.converter.UserConverter;
import com.example.individual.repository.UserRepository;
import com.example.individual.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetUsersUseCaseImpl implements GetUsersUseCase {
    private UserRepository userRepository;

    @Override
    public GetAllUsersResponse getUsers(final GetAllUsersRequest request) {

        final GetAllUsersResponse response = new GetAllUsersResponse();

        response.setUsers(userRepository.findAll());

        return response;
    }
}
