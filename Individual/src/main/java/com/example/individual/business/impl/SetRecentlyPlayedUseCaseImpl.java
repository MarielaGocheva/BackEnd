package com.example.individual.business.impl;

import com.example.individual.business.SetRecentlyPlayedUseCase;
import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.business.converter.RecentlyPlayedConverter;
import com.example.individual.business.converter.SongConverter;
import com.example.individual.business.exceptions.SongDoesNotExist;
import com.example.individual.business.exceptions.UserNotFoundException;
import com.example.individual.domain.*;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.RecentlyPlayedRepository;
import com.example.individual.repository.SongRepository;
import com.example.individual.repository.UserRepository;
import com.example.individual.repository.entity.RecentlyPlayedEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SetRecentlyPlayedUseCaseImpl implements SetRecentlyPlayedUseCase {
    private PlaylistRepository playlistRepository;
    private RecentlyPlayedRepository recentlyPlayedRepository;
    private SongRepository songRepository;
    private UserRepository userRepository;
    @Override
    public SetRecentlyPlayedResponse setRecentlyPlayed(SetRecentlyPlayedRequest request) {
        if(userRepository.existsById(request.getUserId())){
            if(request.getPlaylistTitle() != null && !request.getSongUri().equals("")){
                Playlist pl = PlaylistConverter.convertToPlaylist(playlistRepository.findByTitleAndUserId(request.getPlaylistTitle(), request.getUserId()));
                Song song = SongConverter.convertToSong(songRepository.findBySongUri(request.getSongUri()));
                RecentlyPlayed recentlyPlayed = RecentlyPlayed.builder().userId(request.getUserId()).songId(song.getId()).playlistId(pl.getId()).build();
                recentlyPlayedRepository.save(RecentlyPlayedConverter.convertToRecentlyPlayedEntity(recentlyPlayed));
                return SetRecentlyPlayedResponse.builder().build();
            }
            else if (!request.getSongUri().equals("")){
                Song song = SongConverter.convertToSong(songRepository.findBySongUri(request.getSongUri()));
                RecentlyPlayed recentlyPlayed = RecentlyPlayed.builder().userId(request.getUserId()).songId(song.getId()).build();
                recentlyPlayedRepository.save(RecentlyPlayedConverter.convertToRecentlyPlayedEntity(recentlyPlayed));
                return SetRecentlyPlayedResponse.builder().build();
            }
            else {
                throw new SongDoesNotExist();
            }
        }
        else {
            throw new UserNotFoundException();
        }
    }
}
