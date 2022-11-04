package com.example.individual.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongEntity {
    @Id
    private Long id;
    private String songUri;
    private String artist;
    private int duration;
    private String imageUrl;
    private Long playlistId;
}
