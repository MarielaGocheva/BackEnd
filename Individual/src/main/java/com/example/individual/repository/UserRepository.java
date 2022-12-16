package com.example.individual.repository;

import com.example.individual.domain.User;
import com.example.individual.repository.entity.UserEntity;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsById(Long id);
    boolean existsByEmail(String email);

    UserEntity findUserById(Long id);

    UserEntity save(UserEntity user);

//    int count();
    List<UserEntity> findAll();

    UserEntity findByEmail(String email);
}
