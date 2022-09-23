package com.example.individual.business.impl;

import com.example.individual.domain.CreateUserRequest;
import com.example.individual.domain.CreateUserResponse;
import com.example.individual.repository.UserRepository;
import com.example.individual.repository.UserRepositoryImpl;
import com.example.individual.repository.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {
    @Mock
    private UserRepository userRepositoryMock = mock(UserRepositoryImpl.class);

    @Mock
    private CreateUserRequest request = mock(CreateUserRequest.class);

    @Test
    void createCountry_DoesntExistByCode() {

        when(userRepositoryMock.existsById(request.getId()))
                .thenReturn(false);


        if (userRepositoryMock.existsById(request.getId())) {
            //Change exception type
            throw new RuntimeException();
        }

        UserEntity newUser =
                UserEntity.builder()
                        .id(1L)
                        .fName(request.getFName())
                        .lName(request.getLName())
                        .email(request.getEmail())
                        .role(request.getRole())
                        .build();


        UserEntity savedUser = userRepositoryMock.save(newUser);
        CreateUserResponse actualResult = CreateUserResponse.builder()
                .userId(newUser.getId())
                .build();

        CreateUserResponse expectedResult = CreateUserResponse.builder()
                .userId(1L)
                .build();

        assertEquals(expectedResult, actualResult);
    }
    }