package com.example.individual.business.impl;

import com.example.individual.business.GetRecentlyPlayedUseCase;
import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.business.converter.RecentlyPlayedConverter;
import com.example.individual.business.converter.SongConverter;
import com.example.individual.business.exceptions.InsufficientNumberOfLikedPlaylists;
import com.example.individual.business.exceptions.UserNotFoundException;
import com.example.individual.domain.GetRecentlyPlayedRequest;
import com.example.individual.domain.GetRecentlyPlayedResponse;
import com.example.individual.domain.RecentlyPlayed;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.RecentlyPlayedRepository;
import com.example.individual.repository.SongRepository;
import com.example.individual.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetRecentlyPlayedUseCaseImpl implements GetRecentlyPlayedUseCase {
    private SongRepository songRepository;
    private PlaylistRepository playlistRepository;
    private UserRepository userRepository;
    private RecentlyPlayedRepository recentlyPlayedRepository;
    @Override
    public GetRecentlyPlayedResponse getRecentlyPlayed(GetRecentlyPlayedRequest request) {
        if(userRepository.existsById(request.getUserId())){
           RecentlyPlayed recentlyPlayed = RecentlyPlayedConverter.convertToRecentlyPlayed(
                   recentlyPlayedRepository.findTop1ByUserIdOrderByIdDesc(request.getUserId()));
           if(recentlyPlayed.getPlaylistId() == null){
               return GetRecentlyPlayedResponse.builder().song(SongConverter.convertToSong(songRepository.findBySongId(recentlyPlayed.getSongId()))).build();
           }
           else if(recentlyPlayed.getSongId() != null) {
               return GetRecentlyPlayedResponse.builder().playlist(PlaylistConverter.convertToPlaylist(playlistRepository.
                       findByPlaylistId(recentlyPlayed.getPlaylistId()))).song(SongConverter.convertToSong(songRepository.findBySongId(recentlyPlayed.getSongId()))).build();
           }
           else {
               throw new NullPointerException();
           }
        }
        else {
            throw new UserNotFoundException();
        }
    }
}
