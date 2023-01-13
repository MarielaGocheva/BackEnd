package com.example.individual.repository;

import com.example.individual.repository.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

    @Query(value="SELECT * FROM genres WHERE genre_id = :genreId", nativeQuery = true)
    GenreEntity findByGenreId(@Param("genreId") Long genreId);
}
