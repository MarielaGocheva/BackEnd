package com.example.individual.business.impl;

import com.example.individual.business.GetUserUseCase;
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
    public Optional<User> getUser(long userId) {
        return userRepository.findById(userId).map(UserConverter::convert);
    }
}
