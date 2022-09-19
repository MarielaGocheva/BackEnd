package com.example.individual.business.impl;


import com.example.individual.business.CreateUserUseCase;
import com.example.individual.domain.CreateUserRequest;
import com.example.individual.domain.CreateUserResponse;
import com.example.individual.repository.UserRepository;
import com.example.individual.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsById(request.getId())) {
            //Change the exception type
            throw new RuntimeException();
        }

        UserEntity savedUser = saveNewUser(request);

        return CreateUserResponse.builder()
                .userId(savedUser.getId())
                .build();
    }

    private UserEntity saveNewUser(CreateUserRequest request) {
        UserEntity newUser = UserEntity.builder()
                //Missing information
                .id(request.getId())
                .fName(request.getFName())
                .lName(request.getLName())
                .email(request.getEmail())
                .role(request.getRole())
                .build();
        return userRepository.save(newUser);
    }
}
