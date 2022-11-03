package com.example.individual.configuration.db;

import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.SongRepository;
import com.example.individual.repository.UserRepository;
import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.SongEntity;
import com.example.individual.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class DatabaseDataInitializer {
    private UserRepository userRepository;
    private PlaylistRepository playlistRepository;
    private SongRepository songRepository;
    private List<SongEntity> songs = new ArrayList<>();

    @EventListener(ApplicationReadyEvent.class)
    public void populateDatabaseInitialDummyData() {
        if (userRepository.count() == 0) {
            userRepository.save(UserEntity.builder().id(1L).email("gmail").fName("Paula").lName("Zarate").role("DJ").build());
            userRepository.save(UserEntity.builder().id(2L).email("gmail").fName("Stela").lName("Ivanova").role("RU").build());
            userRepository.save(UserEntity.builder().id(3L).email("gmail").fName("Sebastian").lName("Soto").role("DJ").build());
        }
        if(playlistRepository.count() == 0){
            playlistRepository.save(PlaylistEntity.builder().id(1L).userId(1L).duration(120).songs(songs).build());
            playlistRepository.save(PlaylistEntity.builder().id(2L).userId(1L).duration(128).songs(songs).build());
            playlistRepository.save(PlaylistEntity.builder().id(3L).userId(1L).duration(360).songs(songs).build());
            playlistRepository.save(PlaylistEntity.builder().id(4L).userId(2L).duration(540).songs(songs).build());
        }
    }
}
