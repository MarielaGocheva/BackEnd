package com.example.individual.business.impl;


import com.example.individual.business.CreateUserUseCase;
import com.example.individual.business.converter.UserConverter;
import com.example.individual.business.exceptions.UserAlreadyExists;
import com.example.individual.domain.CreateUserRequest;
import com.example.individual.domain.CreateUserResponse;
import com.example.individual.domain.User;
import com.example.individual.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
//        if (userRepository.existsByEmail(request.getEmail())) {
//            throw new UserAlreadyExists();
//        }

        User savedUser = saveNewUser(request);

        return CreateUserResponse.builder()
                .userId(savedUser.getId())
                .build();
    }

    private User saveNewUser(CreateUserRequest request) {
        User newUser = User.builder()
                .fName(request.getFName())
                .lName(request.getLName())
                .email(request.getEmail())
                .role(request.getRole())
                .password(request.getPassword())
                .build();
        return UserConverter.convertToUser(userRepository.save(UserConverter.convertToUserEntity(newUser)));
    }
}
