package com.example.individual.business.impl;

import com.example.individual.domain.User;
import com.example.individual.repository.entity.UserEntity;

final class UserConverter {
    private UserConverter() {
    }

    public static User convert(UserEntity user) {
        return User.builder()
                .id(user.getId())
                .fName(user.getFName())
                .lName(user.getLName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
