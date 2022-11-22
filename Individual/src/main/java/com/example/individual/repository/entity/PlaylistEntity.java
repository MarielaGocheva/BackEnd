package com.example.individual.repository.entity;

import lombok.*;

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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

//    @NotNull
    @JoinColumn(name = "userId")
    private Long userId;

    @Column (name = "duration")
//    @Length(max = 10)
    @EqualsAndHashCode.Exclude
    private Double duration;

    @OneToMany(mappedBy = "playlist")
    private List<SongEntity> songs;
}
