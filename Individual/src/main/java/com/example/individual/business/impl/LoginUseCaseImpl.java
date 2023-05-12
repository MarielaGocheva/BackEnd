package com.example.individual.business.impl;

import com.example.individual.business.AccessTokenEncoder;
import com.example.individual.business.LoginUseCase;
import com.example.individual.business.exceptions.EmailNotFoundException;
import com.example.individual.business.exceptions.PasswordIncorrectException;
import com.example.individual.business.exceptions.UserNotFoundException;
import com.example.individual.domain.AccessToken;
import com.example.individual.domain.LoginRequest;
import com.example.individual.domain.LoginResponse;
import com.example.individual.domain.enums.Role;
import com.example.individual.repository.UserRepository;
import com.example.individual.repository.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;
    @Override
    public LoginResponse login(LoginRequest request) {
        List<String> roles = new ArrayList<>();
        if(!userRepository.existsByEmail(request.getEmail())){
            throw new UserNotFoundException();
        }
        UserEntity userEntity = userRepository.findByEmail(request.getEmail());
        if(!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())){
            throw new PasswordIncorrectException();
        }
        if(userEntity.getRole().equals("CLIENT")){
            roles.add(Role.CLIENT.toString());
        }
        else if(userEntity.getRole().equals("DJ")){
            roles.add(Role.DJ.toString());
        }
        String accessToken =  accessTokenEncoder.encode(AccessToken.builder().subject(userEntity.getEmail()).userId(userEntity.getId())
                .roles(roles).build());
        return LoginResponse.builder().accessToken(accessToken).build();
    }
}
