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
        UserEntity userEntity = userRepository.findByEmail(request.getEmail());
        List<String> roles = new ArrayList<>();
        if(userEntity == null){
            throw new EmailNotFoundException();
        }
        if(!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())){
            throw new PasswordIncorrectException();
        }
        if(userEntity.getId().equals(null)){
            throw new UserNotFoundException();
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
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final AccessTokenEncoder accessTokenEncoder;

//    @Override
//    public LoginResponse login(LoginRequest loginRequest) {
//        User user = userRepository.findByEmail(loginRequest.getEmail());
//        if (user == null) {
//            throw new InvalidCredentialsException();
//        }
//
//        if (!matchesPassword(loginRequest.getPassword(), user.getPassword())) {
//            System.out.println("Request pass: " + loginRequest.getPassword());
//            System.out.println("User pass" + user.getPassword());
//            throw new InvalidCredentialsException();
//        }
//
//        String accessToken = generateAccessToken(user);
//        return LoginResponse.builder().accessToken(accessToken).build();
//    }
//
//    private boolean matchesPassword(String rawPassword, String encodedPassword) {
//        if(encodedPassword.length() < 10){
//            return rawPassword == encodedPassword;
//        }
//        return passwordEncoder.matches(rawPassword, encodedPassword);
//    }
//
//    private String generateAccessToken(User user) {
////        Long studentId = user.getStudent() != null ? user.getStudent().getId() : null;
////        List<String> roles = user.getUserRoles().stream()
////                .map(userRole -> userRole.getRole().toString())
////                .toList();
//
//        return accessTokenEncoder.encode(
//                AccessToken.builder()
//                        .email(user.getEmail())
//                        .role(user.getRole())
//                        .userId(user.getId())
//                        .build());
//    }

}
