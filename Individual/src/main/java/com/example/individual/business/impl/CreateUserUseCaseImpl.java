package com.example.individual.business.impl;


import com.example.individual.business.CreateUserUseCase;
import com.example.individual.business.converter.UserConverter;
import com.example.individual.business.exceptions.UserAlreadyExists;
import com.example.individual.domain.CreateUserRequest;
import com.example.individual.domain.CreateUserResponse;
import com.example.individual.domain.User;
import com.example.individual.domain.enums.Role;
import com.example.individual.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExists();
        }
        else {
            User savedUser = saveNewUser(request);
            return CreateUserResponse.builder()
                    .userId(savedUser.getId())
                    .build();
        }
    }

    private User saveNewUser(CreateUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User newUser = User.builder()
                .fName(request.getFName())
                .lName(request.getLName())
                .email(request.getEmail())
                .role(Role.valueOf(request.getRole()))
                .password(encodedPassword)
                .build();
        return UserConverter.convertToUser(userRepository.save(UserConverter.convertToUserEntity(newUser)));
    }
}
