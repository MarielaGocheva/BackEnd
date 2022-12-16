package com.example.individual.repository;

import com.example.individual.domain.User;
import com.example.individual.repository.entity.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository  {
    boolean existsById(Long id);

    User findById(Long id);

    User save(User user);

    int count();
    List<User> findAll();
}
