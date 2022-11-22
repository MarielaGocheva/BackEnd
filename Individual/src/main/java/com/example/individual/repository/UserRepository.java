package com.example.individual.repository;

import com.example.individual.domain.User;

import java.util.List;

public interface UserRepository  {
    boolean existsById(Long id);

    User findById(Long id);

    User save(User user);

    int count();
    List<User> findAll();
}
