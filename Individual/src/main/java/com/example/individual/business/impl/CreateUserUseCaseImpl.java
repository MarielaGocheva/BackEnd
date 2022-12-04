package com.example.individual.business.impl;


import com.example.individual.business.CreateUserUseCase;
import com.example.individual.business.exceptions.UserAlreadyExists;
import com.example.individual.domain.CreateUserRequest;
import com.example.individual.domain.CreateUserResponse;
import com.example.individual.domain.User;
import com.example.individual.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).equals(null)) {
            throw new UserAlreadyExists();
        }

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
        return userRepository.save(newUser);
    }
}
