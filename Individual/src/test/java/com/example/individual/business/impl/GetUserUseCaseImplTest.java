package com.example.individual.business.impl;

import com.example.individual.business.converter.UserConverter;
import com.example.individual.business.exceptions.UserNotFoundException;
import com.example.individual.domain.GetUserRequest;
import com.example.individual.domain.GetUserResponse;
import com.example.individual.domain.User;
import com.example.individual.domain.enums.Role;
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
    void getUser_valid(){
        GetUserRequest request = GetUserRequest.builder().id(2L).build();
        User stela = User.builder().id(2L).fName("Stela").lName("Ivanova").email("sela@gmail").role(Role.DJ).build();
        when(userRepositoryMock.existsById(request.getId())).thenReturn(true);
        when(userRepositoryMock.findUserById(request.getId())).thenReturn(UserConverter.convertToUserEntity(stela));
        GetUserResponse actual = getUserUseCase.getUser(request);
        GetUserResponse expected = GetUserResponse.builder().user(stela).build();
        assertEquals(expected.getUser().getFName(), actual.getUser().getFName());
        verify(userRepositoryMock).findUserById(request.getId());
    }

    @Test
    void getUser_userDoesNotExist(){
        GetUserRequest request = GetUserRequest.builder().id(2L).build();
        assertThrows(UserNotFoundException.class, () -> getUserUseCase.getUser(request));
    }

    @Test
    void getUser_userIsNull(){
        GetUserRequest request = GetUserRequest.builder().id(null).build();
        assertThrows(UserNotFoundException.class, () -> getUserUseCase.getUser(request));
    }
}