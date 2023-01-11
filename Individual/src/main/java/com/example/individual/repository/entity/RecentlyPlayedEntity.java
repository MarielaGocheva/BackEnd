package com.example.individual.repository.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "recently_played")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentlyPlayedEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @JoinColumn(name = "user_id")
    private Long userId;
    @JoinColumn(name = "playlist_id")
    private Long playlistId;
    @JoinColumn(name = "song_id")
    private Long songId;
}
