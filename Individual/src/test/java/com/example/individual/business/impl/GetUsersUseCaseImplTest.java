package com.example.individual.business.impl;

import com.example.individual.business.converter.UserConverter;
import com.example.individual.domain.GetAllUsersRequest;
import com.example.individual.domain.GetAllUsersResponse;
import com.example.individual.domain.User;
import com.example.individual.domain.enums.Role;
import com.example.individual.repository.UserRepository;
import com.example.individual.repository.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUsersUseCaseImplTest {
    @Mock
    private UserRepository userRepositoryMock;
    @InjectMocks
    private GetUsersUseCaseImpl getUsersUseCase;

    @Test
    void getUsers_valid(){
        GetAllUsersRequest request = GetAllUsersRequest.builder().build();
        List<User> users = new ArrayList<>();
        users.add(User.builder().id(2L).fName("Stela").lName("Ivanova").email("sela@gmail").role(Role.DJ).build());
        users.add(User.builder().id(3L).fName("Paula").lName("Zarate").email("paula@gmail").role(Role.DJ).build());
        List<UserEntity> userEntities = users.stream().map(UserConverter::convertToUserEntity).toList();
        when(userRepositoryMock.findAll()).thenReturn(userEntities);
        GetAllUsersResponse actual = getUsersUseCase.getUsers(request);
        GetAllUsersResponse expected = GetAllUsersResponse.builder().users(users).build();
        assertEquals(expected.getUsers().size(), actual.getUsers().size());
        verify(userRepositoryMock).findAll();
    }
}