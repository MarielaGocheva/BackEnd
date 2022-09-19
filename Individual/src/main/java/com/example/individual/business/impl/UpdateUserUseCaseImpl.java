package com.example.individual.business.impl;


import com.example.individual.business.UpdateUserUseCase;
import com.example.individual.domain.UpdateUserRequest;
import com.example.individual.repository.UserRepository;
import com.example.individual.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private final UserRepository userRepository;

    @Override
    public void updateUser(UpdateUserRequest request) {
        Optional<UserEntity> userOptional = userRepository.findById(request.getId());
        if (userOptional.isEmpty()) {
            //change the exception type
            throw new RuntimeException();
        }

        UserEntity user = userOptional.orElse(null);

        updateFields(request, user);
    }

    private void updateFields(UpdateUserRequest request, UserEntity user) {
        user.setRole(request.getRole());
        user.setEmail(request.getEmail());
        user.setFName(request.getFName());
        user.setLName(request.getLName());

        userRepository.save(user);
    }
}
