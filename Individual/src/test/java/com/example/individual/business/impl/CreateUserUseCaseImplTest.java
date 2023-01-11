package com.example.individual.business.impl;

import com.example.individual.business.converter.UserConverter;
import com.example.individual.business.exceptions.UserAlreadyExists;
import com.example.individual.domain.CreateUserRequest;
import com.example.individual.domain.CreateUserResponse;
import com.example.individual.domain.User;
import com.example.individual.domain.enums.Role;
import com.example.individual.repository.UserRepository;
import com.example.individual.repository.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCaseImpl;

    @Test
    void createUser_DoesntExistByEmail() {
        CreateUserRequest request = CreateUserRequest.builder().email("gmail").fName("Stela").lName("Ivanova").password("123").role("DJ").build();
        User user = User.builder().id(1L).email("gmail").fName("Stela").lName("Ivanova").password(passwordEncoder.encode("123")).role(Role.DJ).build();
        UserEntity userEntity = UserEntity.builder().email("gmail").fName("Stela").lName("Ivanova").password(passwordEncoder.encode("123")).role("DJ").build();
        when(userRepositoryMock.existsByEmail(request.getEmail()))
                .thenReturn(false);
        when(userRepositoryMock.save(userEntity)).thenReturn(UserConverter.convertToUserEntity(user));
        CreateUserResponse actualResult = createUserUseCaseImpl.createUser(request);
        CreateUserResponse expectedResult = CreateUserResponse.builder().userId(1L).build();
        assertEquals(expectedResult.getUserId(), actualResult.getUserId());
        verify(userRepositoryMock).save(userEntity);
    }

    @Test
    void createUser_ExistsByEmail(){
        CreateUserRequest request = CreateUserRequest.builder().email("gmail").fName("Stela").lName("Ivanova").password("123").role("DJ").build();
        when(userRepositoryMock.existsByEmail(request.getEmail())).thenThrow(UserAlreadyExists.class);
        assertThrows(UserAlreadyExists.class, () -> createUserUseCaseImpl.createUser(request));
    }
}