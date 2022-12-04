package com.example.individual.repository.converter;

import com.example.individual.domain.User;
import com.example.individual.repository.entity.UserEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserConverter {
    public User convertToUser(UserEntity user) {
        return User.builder()
                .id(user.getId())
                .fName(user.getFName())
                .lName(user.getLName())
                .email(user.getEmail())
                .role(user.getRole())
                .password(user.getPassword())
                .build();
    }

    public UserEntity convertToUserEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .fName(user.getFName())
                .lName(user.getLName())
                .email(user.getEmail())
                .role(user.getRole())
                .password(user.getPassword())
                .build();
    }
}
