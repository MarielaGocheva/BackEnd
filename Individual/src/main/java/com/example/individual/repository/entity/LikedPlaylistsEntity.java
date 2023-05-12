package com.example.individual.repository.entity;

import com.example.individual.domain.Playlist;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "liked_playlists")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikedPlaylistsEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "playlist_id")
    private PlaylistEntity playlist;

    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
