package com.example.individual.business.impl;

import com.example.individual.business.AccessTokenEncoder;
import com.example.individual.business.exceptions.PasswordIncorrectException;
import com.example.individual.business.exceptions.UserNotFoundException;
import com.example.individual.domain.AccessToken;
import com.example.individual.domain.LoginRequest;
import com.example.individual.domain.LoginResponse;
import com.example.individual.domain.enums.Role;
import com.example.individual.repository.UserRepository;
import com.example.individual.repository.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseImplTest {
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private PasswordEncoder passwordEncoderMock;
    @Mock
    private AccessTokenEncoder accessTokenEncoderMock;
    @InjectMocks
    private LoginUseCaseImpl loginUseCase;

    @Test
    void login_valid(){
        LoginRequest request = LoginRequest.builder().email("gmail").password("123").build();
        List<String> roles = new ArrayList<>();
        roles.add("DJ");
        UserEntity stela = UserEntity.builder().id(2L).fName("Stela").lName("Ivanova").email("stela@gmail").role("DJ").build();
        AccessToken accessToken = AccessToken.builder().userId(2L).subject("stela@gmail").roles(roles).build();
        when(userRepositoryMock.existsByEmail(request.getEmail())).thenReturn(true);
        when(userRepositoryMock.findByEmail(request.getEmail())).thenReturn(stela);
        when(passwordEncoderMock.matches(request.getPassword(), stela.getPassword())).thenReturn(true);
        when(accessTokenEncoderMock.encode(accessToken)).thenReturn(accessToken.toString());
        LoginResponse actual = loginUseCase.login(request);
        LoginResponse expected = LoginResponse.builder().accessToken(accessToken.toString()).build();
        assertEquals(expected.getAccessToken(), actual.getAccessToken());
        verify(accessTokenEncoderMock).encode(accessToken);
    }

    @Test
    void login_userDoesNotExist(){
        LoginRequest request = LoginRequest.builder().email("gmail").password("123").build();
        assertThrows(UserNotFoundException.class, () -> loginUseCase.login(request));
    }

    @Test
    void login_emptyCredentials(){
        LoginRequest request = LoginRequest.builder().email(null).password(null).build();
        assertThrows(UserNotFoundException.class, () -> loginUseCase.login(request));
    }

    @Test
    void login_incorrectCredentials(){
        LoginRequest request = LoginRequest.builder().email("gmail").password("123").build();
        List<String> roles = new ArrayList<>();
        roles.add("DJ");
        UserEntity stela = UserEntity.builder().id(2L).fName("Stela").lName("Ivanova").email("stela@gmail").role("DJ").build();
        AccessToken accessToken = AccessToken.builder().userId(2L).subject("stela@gmail").roles(roles).build();
        when(userRepositoryMock.existsByEmail(request.getEmail())).thenReturn(true);
        when(userRepositoryMock.findByEmail(request.getEmail())).thenReturn(stela);
        assertThrows(PasswordIncorrectException.class, () -> loginUseCase.login(request));
    }
}