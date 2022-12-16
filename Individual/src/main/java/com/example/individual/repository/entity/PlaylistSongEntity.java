package com.example.individual.repository.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "playlist_songs")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistSongEntity {
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
    @JoinColumn(name = "song_id", referencedColumnName = "song_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private SongEntity song;
}
