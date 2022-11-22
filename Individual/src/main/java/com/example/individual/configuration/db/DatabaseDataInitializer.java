package com.example.individual.configuration.db;

import com.example.individual.domain.Playlist;
import com.example.individual.domain.Song;
import com.example.individual.domain.User;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.SongRepository;
import com.example.individual.repository.UserRepository;
import com.example.individual.repository.entity.PlaylistEntity;
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
    private List<Song> songs;

    @EventListener(ApplicationReadyEvent.class)
    public void populateDatabaseInitialDummyData() {
        if (userRepository.count() == 0) {
            userRepository.save(User.builder().id(2L).email("gmail").fName("Stela").lName("Ivanova").role("RU").build());
            userRepository.save(User.builder().id(1L).email("gmail").fName("Paula").lName("Zarate").role("DJ").build());
            userRepository.save(User.builder().id(3L).email("gmail").fName("Sebastian").lName("Soto").role("DJ").build());
        }
        if(playlistRepository.count() == 0){
            Song song = Song.builder().songUri("hh").artist("audioslave").imageUrl("dd").duration(5D).build();
            songs.add(song);
            playlistRepository.save(Playlist.builder().id(1L).userId(1L).duration(120D).songs(songs).build());
            playlistRepository.save(Playlist.builder().id(2L).userId(1L).duration(128D).songs(songs).build());
            playlistRepository.save(Playlist.builder().id(3L).userId(1L).duration(360D).songs(songs).build());
            playlistRepository.save(Playlist.builder().id(4L).userId(2L).duration(540D).songs(songs).build());
            playlistRepository.save(Playlist.builder().id(5L).userId(2L).duration(540D).songs(songs).build());
            playlistRepository.save(Playlist.builder().id(6L).userId(2L).duration(540D).songs(songs).build());
            playlistRepository.save(Playlist.builder().id(7L).userId(2L).duration(540D).songs(songs).build());
            playlistRepository.save(Playlist.builder().id(8L).userId(2L).duration(540D).songs(songs).build());
            playlistRepository.save(Playlist.builder().id(9L).userId(2L).duration(540D).songs(songs).build());
            playlistRepository.save(Playlist.builder().id(10L).userId(2L).duration(540D).songs(songs).build());
        }
    }
}
