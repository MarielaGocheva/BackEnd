package com.example.individual.business.impl;

import com.example.individual.domain.User;
import com.example.individual.repository.converter.UserConverter;
import com.example.individual.repository.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserConverterTest {
    @InjectMocks
    private UserConverter userConverter;

    @Test
    void shouldConvertAllUserFieldsToDomain() {
        UserEntity userTobeConverted = UserEntity.builder()
                .id(1L)
                .fName("Paula")
                .lName("Zarate")
                .email("@gmail.com")
                .role("DJ")
                .salt("###")
                .encryptedPass("###")
                .build();

        User actual = userConverter.ConvertToUser(userTobeConverted);

        User expected = User.builder()
                .id(1L)
                .fName("Paula")
                .lName("Zarate")
                .email("@gmail.com")
                .role("DJ")
                .salt("###")
                .encryptedPass("###")
                .build();

        assertEquals(expected, actual);
    }
}