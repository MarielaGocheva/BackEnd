package com.example.individual.business.impl;

import com.example.individual.domain.Song;
import com.example.individual.business.converter.SongConverter;
import com.example.individual.repository.entity.SongEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SongConverterTest {
    @InjectMocks
    private SongConverter songConverter;

    @Test
    void shouldConvertAllSongFieldsToDomain() {
        SongEntity songTobeConverted = SongEntity.builder()
                .id(1L)
                .songUri("###")
                .duration(3D)
                .artist("Alter Bridge")
                .imageUrl("###")
                .build();

        Song actual = songConverter.convertToSong(songTobeConverted);

        Song expected = Song.builder()
                .id(1L)
                .songUri("###")
                .duration(3D)
                .artist("Alter Bridge")
                .imageUrl("###")
                .build();

        assertEquals(expected, actual);
    }

}