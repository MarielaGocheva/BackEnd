package com.example.individual.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name = "genres")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Proxy(lazy = false)
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long id;

    @Column (name = "genre_name")
    private String name;
}
