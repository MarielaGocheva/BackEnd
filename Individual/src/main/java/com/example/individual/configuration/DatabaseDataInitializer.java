package com.example.individual.configuration;

import com.example.individual.business.*;
import com.example.individual.domain.*;
import com.example.individual.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DatabaseDataInitializer {
    private PlaylistRepository playlistRepository;
    private SongRepository songRepository;
    private List<Song> songs;
    private UserRepository userRepository;
    private PlaylistSongRepository playlistSongRepository;
    private GenreRepository genreRepository;
    private PlaylistGenresRepository playlistGenresRepository;
    private GetPlaylistGenresUseCase getPlaylistGenresUseCase;
    private SetPlayedUseCase setPlayedUseCase;
    private LikePlaylistUseCase likePlaylistUseCase;
    private GetRecommendationsUseCase getRecommendationsUseCase;


    @EventListener(ApplicationReadyEvent.class)
    public void populateDatabaseInitialDummyData() {
//        UserEntity user = UserEntity.builder().email("soto@gmail").fName("Sebastiaan").lName("Soto").role("DJ").password("123").build();
//        userRepository.save(user);
//        SongEntity song = SongEntity.builder().songUri("uri").artist("Alter Bridge").imageUrl("img").build();
//        SongEntity saved = songRepository.save(song);
//        SongEntity saved = songRepository.findById(1L).orElse(new SongEntity());
//        SongEntity song = SongEntity.builder().songUri("uri").artist("Extreme").imageUrl("img").build();
//        songs.add(song);
//        SetPlayedRequest request = SetPlayedRequest.builder().playlistId(4L).build();
//            setPlayedUseCase.setPlayed(request);
//        LikePlaylistRequest request = LikePlaylistRequest.builder().playlistId(5L).userId(1L).build();
//        likePlaylistUseCase.likePlaylist(request);
//        List<Song> songs = new ArrayList<>();
//        List<String> genreIds = Arrays.asList("2,13,14,".split(","));
//        List<Genre> genres = genreIds.stream().map(genre -> GenreConverter.convertToGenre(genreRepository.getReferenceById(Long.parseLong(genre)))).toList();
//
//        GetRecommendationsRequest request = GetRecommendationsRequest.builder().userId(1L).build();
//        getRecommendationsUseCase.getRecommendations(request);
//        Playlist playlist = Playlist.builder().userId(2L).title("Party").songs(songs).genres(genres).duration(0D).build();
//        Playlist savedPlaylist = PlaylistConverter.convertToPlaylist(playlistRepository.save(PlaylistConverter.convertToPlaylistEntity(playlist)));
//        for (Genre genre : genres) {
//            playlistGenresRepository.save(PlaylistGenreEntity.builder().genre(GenreConverter.convertToGenreEntity(genre)).playlist(PlaylistConverter.convertToPlaylistEntity(savedPlaylist)).build());
//        }
//        List<PlaylistGenreEntity> pl = playlistGenresRepository.findAllByPlaylistId(savedPlaylist.getId());
//        System.out.println(savedPlaylist.getGenres());

//        GetPlaylistGenresResponse res = getPlaylistGenresUseCase.getGenres(GetPlaylistGenresRequest.builder().playlistId(22L).build());
//        System.out.println(res);

//        GetPlaylistGenresRequest request = GetPlaylistGenresRequest.builder().playlistId(22L).build();
//        System.out.println(getPlaylistGenresUseCase.getGenres(request));

//        PlaylistEntity pl = playlistRepository.findByTitleAndUserId("Gym Paylist", 2L);
//        System.out.println("playlist id: " + pl.getPlaylistId());

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
