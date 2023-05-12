package com.example.individual.repository.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playlist")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Proxy(lazy = false)
public class PlaylistEntity {
    @Id
    @Column(name = "playlist_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playlistId;

    @Length(max = 100)
    @Column(name = "title")
    private String title;

    @NotNull
    @JoinColumn(name = "user_id")
    private Long userId;

    @Column (name = "duration")
    @EqualsAndHashCode.Exclude
    private Double duration;

    @Column (name = "image_url")
    private String imageUrl;

    @Column (name = "plays")
    private Integer plays;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(name = "playlist_songs",
            joinColumns = { @JoinColumn(name = "playlist_id") },
            inverseJoinColumns = { @JoinColumn(name = "song_id") })
    private List<SongEntity> songs = new ArrayList<>();

    @OneToMany(cascade =
            CascadeType.ALL)
    @JoinTable(name = "playlist_genres",
            joinColumns = { @JoinColumn(name = "playlist_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") })
    private List<GenreEntity> genres = new ArrayList<>();
}
