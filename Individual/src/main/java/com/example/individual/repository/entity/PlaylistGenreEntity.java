package com.example.individual.repository.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "playlist_genres")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistGenreEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn (name = "playlist_id", referencedColumnName = "playlist_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private PlaylistEntity playlist;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "genre_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private GenreEntity genre;
}
