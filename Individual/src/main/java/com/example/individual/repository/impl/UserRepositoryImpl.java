package com.example.individual.repository.impl;

import com.example.individual.domain.User;
import com.example.individual.repository.UserRepository;
import com.example.individual.repository.converter.UserConverter;
import com.example.individual.repository.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static long NEXT_ID = 1;
    private final List<UserEntity> savedUsers;
    private UserConverter userConverter;

    public UserRepositoryImpl() {

        this.savedUsers = new ArrayList<>();
        this.userConverter = new UserConverter();
    }


    @Override
    public User findById(Long userId) {
        for (UserEntity user : savedUsers){
            if (user.getId() == userId){
                return userConverter.convertToUser(user);
            }
        }
        return null;
    }

    @Override
    public User save(User user) {
        user.setId(NEXT_ID);
        NEXT_ID++;
        this.savedUsers.add(userConverter.convertToUserEntity(user));
        return user;
    }

    @Override
    public boolean existsById(Long id) {
        for (UserEntity user : this.savedUsers
        ) {
            if (user.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int count() {
        return this.savedUsers.size();
    }

    @Override
    public List<User> findAll() {
        return Collections.unmodifiableList(savedUsers.stream().map(userEntity -> userConverter.convertToUser(userEntity)).toList());
    }

}
