package com.example.individual.repository.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "song")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column (name = "songUri")
    private String songUri;

    @Column(name = "artist")
    private String artist;

    @Column(name = "duration")
    private Double duration;

    @Column(name = "imageUrl")
    private String imageUrl;
}
