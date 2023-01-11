package com.example.individual.repository.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "song")
//@SecondaryTables(@SecondaryTable(name="playlist_songs", pkJoinColumns=@PrimaryKeyJoinColumn(name="song_id")))
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private Long songId;

    @Column (name = "song_uri")
    private String songUri;

    @Column(name = "artist")
    private String artist;

    @Column (name = "title")
    private String title;

    @Column(name = "duration")
    private Double duration;

    @Column(name = "image_url")
    private String imageUrl;

//    @Column(name = "playlist_id")
//    @JoinColumn(name = "playlist_id")
//    private Long playlistId;
}
