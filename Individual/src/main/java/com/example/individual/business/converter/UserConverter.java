package com.example.individual.business.converter;

import com.example.individual.domain.User;
import com.example.individual.domain.enums.Role;
import com.example.individual.repository.entity.UserEntity;

public final class UserConverter {
    public UserConverter(){
        //Constructor
    }
    public static User convertToUser(UserEntity user) {
        return User.builder()
                .id(user.getId())
                .fName(user.getFName())
                .lName(user.getLName())
                .email(user.getEmail())
                .role(Role.valueOf(user.getRole()))
                .password(user.getPassword())
                .build();
    }

    public static UserEntity convertToUserEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .fName(user.getFName())
                .lName(user.getLName())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .password(user.getPassword())
                .build();
    }
}
