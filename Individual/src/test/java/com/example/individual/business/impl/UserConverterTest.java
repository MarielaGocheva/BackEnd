package com.example.individual.business.impl;

import com.example.individual.business.converter.UserConverter;
import com.example.individual.domain.User;
import com.example.individual.domain.enums.Role;
import com.example.individual.repository.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserConverterTest {

    @Test
    void shouldConvertAllUserEntitiesToDomain() {
        UserEntity userTobeConverted = UserEntity.builder()
                .id(1L)
                .fName("Paula")
                .lName("Zarate")
                .email("@gmail.com")
                .role("DJ")
                .password("###")
                .build();

        User user = UserConverter.convertToUser(userTobeConverted);
        Long actualId = user.getId();
        String actualFName = user.getFName();
        String actualEmail = user.getEmail();
        Role actualRole = user.getRole();

        User expected = User.builder()
                .id(1L)
                .fName("Paula")
                .lName("Zarate")
                .email("@gmail.com")
                .role(Role.valueOf("DJ"))
                .password("###")
                .build();

        assertEquals(expected.getId(), actualId);
        assertEquals(expected.getFName(), actualFName);
        assertEquals(expected.getEmail(), actualEmail);
        assertEquals(expected.getRole(), actualRole);
    }

    @Test
    void shouldConvertAllUserDomainObjectsToEntities() {
        User userTobeConverted = User.builder()
                .id(1L)
                .fName("Paula")
                .lName("Zarate")
                .email("@gmail.com")
                .role(Role.valueOf("DJ"))
                .password("###")
                .build();

        UserEntity user = UserConverter.convertToUserEntity(userTobeConverted);
        Long actualId = user.getId();
        String actualFName = user.getFName();
        String actualEmail = user.getEmail();
        String actualRole = user.getRole();

        User expected = User.builder()
                .id(1L)
                .fName("Paula")
                .lName("Zarate")
                .email("@gmail.com")
                .role(Role.valueOf("DJ"))
                .password("###")
                .build();

        assertEquals(expected.getId(), actualId);
        assertEquals(expected.getFName(), actualFName);
        assertEquals(expected.getEmail(), actualEmail);
        assertEquals(expected.getRole().toString(), actualRole);
    }
}