package com.example.individual.business.impl;

import com.example.individual.domain.Song;
import com.example.individual.business.converter.SongConverter;
import com.example.individual.repository.entity.SongEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SongConverterTest {
    @Test
    void shouldConvertAllSongEntitiesToDomain() {
        SongEntity songTobeConverted = SongEntity.builder()
                .songId(1L)
                .songUri("###")
                .duration(3D)
                .artist("Alter Bridge")
                .imageUrl("###")
                .title("One Day Remains")
                .build();

        Song actual = SongConverter.convertToSong(songTobeConverted);

        Song expected = Song.builder()
                .id(1L)
                .songUri("###")
                .duration(3D)
                .artist("Alter Bridge")
                .imageUrl("###")
                .title("One Day Remains")
                .build();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getSongUri(), actual.getSongUri());
        assertEquals(expected.getDuration(), actual.getDuration());
        assertEquals(expected.getArtist(), actual.getArtist());
        assertEquals(expected.getImageUrl(), actual.getImageUrl());
    }

    @Test
    void shouldConvertAllSongDomainObjectToEntities() {
        Song songTobeConverted = Song.builder()
                .id(1L)
                .songUri("###")
                .duration(3D)
                .artist("Alter Bridge")
                .imageUrl("###")
                .title("One Day Remains")
                .build();

        SongEntity actual = SongConverter.convertToSongEntity(songTobeConverted);

        Song expected = Song.builder()
                .id(1L)
                .songUri("###")
                .duration(3D)
                .artist("Alter Bridge")
                .imageUrl("###")
                .title("One Day Remains")
                .build();

        assertEquals(expected.getId(), actual.getSongId());
        assertEquals(expected.getSongUri(), actual.getSongUri());
        assertEquals(expected.getDuration(), actual.getDuration());
        assertEquals(expected.getArtist(), actual.getArtist());
        assertEquals(expected.getImageUrl(), actual.getImageUrl());
    }

}