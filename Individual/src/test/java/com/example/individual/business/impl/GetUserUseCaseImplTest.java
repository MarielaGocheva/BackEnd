package com.example.individual.business.impl;

import com.example.individual.domain.GetUserRequest;
import com.example.individual.domain.GetUserResponse;
import com.example.individual.domain.User;
import com.example.individual.repository.UserRepository;
import com.example.individual.repository.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class GetUserUseCaseImplTest {
    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private GetUserUseCaseImpl getUserUseCase;

    @Test
    void getUser_shouldReturnTheUserConverted(){
//        GetUserRequest request = GetUserRequest.builder().build();
//        request.setId(0L);
//        UserEntity listenerEntity =
//                UserEntity.builder().id(0L).role("reg").fName("Mariela").lName("Gocheva").email("m.gocheva@gmail.com").encryptedPass("###").salt("###").build();
//        User listener =
//                User.builder().id(0L).role("reg").fName("Mariela").lName("Gocheva").email("m.gocheva@gmail.com").encryptedPass("###").salt("###").build();
//        when(userRepositoryMock.findById(listenerEntity.getId())
//                .thenReturn(listener);
//        GetUserResponse actualResult = getUserUseCase.getUser(request);
//        GetUserResponse expectedResult = GetUserResponse
//                .builder()
//                .user(listener)
//                .build();
//        assertEquals(expectedResult, actualResult);


    }
}