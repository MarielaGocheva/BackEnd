package com.example.individual.configuration;

import com.example.individual.domain.Playlist;
import com.example.individual.domain.Song;
import com.example.individual.domain.User;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.PlaylistSongRepository;
import com.example.individual.repository.SongRepository;
import com.example.individual.repository.UserRepository;
import com.example.individual.repository.entity.PlaylistEntity;
import com.example.individual.repository.entity.PlaylistSongEntity;
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
    private PlaylistRepository playlistRepository;
    private SongRepository songRepository;
    private List<Song> songs;
    private UserRepository userRepository;
    private PlaylistSongRepository playlistSongRepository;


    @EventListener(ApplicationReadyEvent.class)
    public void populateDatabaseInitialDummyData() {
//        UserEntity user = UserEntity.builder().email("soto@gmail").fName("Sebastiaan").lName("Soto").role("DJ").password("123").build();
//        userRepository.save(user);
        List<SongEntity> songs = new ArrayList<>();
//        SongEntity song = SongEntity.builder().songUri("uri").artist("Alter Bridge").imageUrl("img").build();
//        SongEntity saved = songRepository.save(song);
//        SongEntity saved = songRepository.findById(1L).orElse(new SongEntity());
//        SongEntity song = SongEntity.builder().songUri("uri").artist("Extreme").imageUrl("img").build();
//        songs.add(song);


//        SongEntity song = SongEntity.builder().songUri("https://open.spotify.com/track/30DnQCN64v8xBpGZpLgb6l?si=d339188f1289402d").artist("Sia").imageUrl("img").build();
//        songRepository.save(song);
//        PlaylistEntity playlist = playlistRepository.findById(5L).get();
//        PlaylistSongEntity pl = PlaylistSongEntity.builder().playlist(playlist).song(song).build();
//        playlistSongRepository.save(pl);


//            System.out.println(playlistSongRepository.findSongsByPlaylistId(4L).stream().map(song -> song.getId()));
//        PlaylistEntity pl = PlaylistEntity.builder().title("Top 10 Christmas Songs").userId(2L).duration(120D).build();
//        playlistRepository.save(pl);
//        playlistSongRepository.save(PlaylistSongEntity.builder().playlist(pl).song(saved).build());



//        if (userRepository.count() == 0) {
//            userRepository.save(User.builder().id(2L).email("gmail").fName("Stela").lName("Ivanova").role("RU").password("123").build());
//            userRepository.save(User.builder().id(1L).email("gmail").fName("Paula").lName("Zarate").role("DJ").password("123").build());
//            userRepository.save(User.builder().id(3L).email("gmail").fName("Sebastian").lName("Soto").role("DJ").password("123").build());
//        }
//        if(playlistRepository.count() == 0){
//            Song song = Song.builder().songUri("hh").artist("audioslave").imageUrl("dd").duration(5D).build();
//            songs.add(song);
//            playlistRepository.save(Playlist.builder().id(1L).title("Top 10 Christmas Songs").userId(1L).duration(120D).songs(songs).build());
//            playlistRepository.save(Playlist.builder().id(2L).title("Chill Vibes").userId(1L).duration(128D).songs(songs).build());
//            playlistRepository.save(Playlist.builder().id(3L).title("Gym Playlist").userId(1L).duration(360D).songs(songs).build());
//            playlistRepository.save(Playlist.builder().id(4L).title("Playlist for homework").userId(2L).duration(540D).songs(songs).build());
//            playlistRepository.save(Playlist.builder().id(5L).title("Travel Playlist").userId(2L).duration(540D).songs(songs).build());
//            playlistRepository.save(Playlist.builder().id(6L).title("Summer Playlist").userId(2L).duration(540D).songs(songs).build());
//            playlistRepository.save(Playlist.builder().id(7L).title("Party Playlist").userId(2L).duration(540D).songs(songs).build());
//            playlistRepository.save(Playlist.builder().id(8L).title("Sad Playlist").userId(2L).duration(540D).songs(songs).build());
//            playlistRepository.save(Playlist.builder().id(9L).title("Groovy Playlist").userId(2L).duration(540D).songs(songs).build());
//            playlistRepository.save(Playlist.builder().id(10L).title("Endless Funk").userId(2L).duration(540D).songs(songs).build());
//        }
    }
}
