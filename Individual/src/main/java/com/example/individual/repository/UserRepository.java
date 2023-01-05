package com.example.individual.repository;

import com.example.individual.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsById(Long id);
    boolean existsByEmail(String email);

    UserEntity findUserById(Long id);

    UserEntity save(UserEntity user);

//    int count();
    List<UserEntity> findAll();

    UserEntity findByEmail(String email);
    @Query(value = "SELECT *, CONCAT(u.f_Name, ' ', u.l_Name) AS name FROM user u WHERE CONCAT(u.f_Name, ' ', u.l_Name) LIKE LOWER(CONCAT('%', ?1,'%')) ",
            nativeQuery = true)
    List<UserEntity> findAllByWholeName(@Param("search") String search);
}
