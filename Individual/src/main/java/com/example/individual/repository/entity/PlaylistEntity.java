package com.example.individual.repository.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "playlist")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistEntity {
    @Id
    @Column(name = "playlist_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playlistId;

    @Column(name = "title")
    private String title;

//    @NotNull
//    @OneToOne
    @JoinColumn(name = "user_id")
    private Long userId;

    @Column (name = "duration")
//    @Length(max = 10)
    @EqualsAndHashCode.Exclude
    private Double duration;

    @Column (name = "image_url")
    private String imageUrl;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "playlist_songs",
            joinColumns = { @JoinColumn(name = "playlist_id") },
            inverseJoinColumns = { @JoinColumn(name = "song_id") })
    private List<SongEntity> songs;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
//    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "playlist_genres",
            joinColumns = { @JoinColumn(name = "playlist_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") })
    private List<GenreEntity> genres;




//    @OneToMany(mappedBy="playlist", fetch=FetchType.EAGER)
//    @Fetch(value = FetchMode.SUBSELECT)
}
