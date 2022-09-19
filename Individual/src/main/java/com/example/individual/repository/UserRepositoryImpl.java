package com.example.individual.repository;

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

    public UserRepositoryImpl() {
        this.savedUsers = new ArrayList<>();
    }


    @Override
    public Optional<UserEntity> findById(Long userId) {
        return this.savedUsers.stream()
                .filter(userEntity -> userEntity.getId().equals(userId))
                .findFirst();
    }

    @Override
    public UserEntity save(UserEntity user) {
        user.setId(NEXT_ID);
        NEXT_ID++;
        this.savedUsers.add(user);
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
    public List<UserEntity> findAll() {
        return Collections.unmodifiableList(this.savedUsers);
    }

}
