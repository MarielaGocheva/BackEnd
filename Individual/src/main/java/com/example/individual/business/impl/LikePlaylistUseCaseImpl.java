package com.example.individual.business.impl;

import com.example.individual.business.LikePlaylistUseCase;
import com.example.individual.business.converter.LikedPlaylistsConverter;
import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.business.converter.UserConverter;
import com.example.individual.business.exceptions.PlaylistDoesNotExist;
import com.example.individual.business.exceptions.UserNotFoundException;
import com.example.individual.domain.LikePlaylistRequest;
import com.example.individual.domain.LikedPlaylist;
import com.example.individual.repository.LikedPlaylistsRepository;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikePlaylistUseCaseImpl implements LikePlaylistUseCase {
    private LikedPlaylistsRepository likedPlaylistsRepository;
    private UserRepository userRepository;
    private PlaylistRepository playlistRepository;

    @Override
    public void likePlaylist(LikePlaylistRequest request) {
        if(!userRepository.existsById(request.getUserId())){
            throw new UserNotFoundException();
        }
        else if (!playlistRepository.existsById(request.getPlaylistId())){
            throw new PlaylistDoesNotExist();
        }
        else {
            LikedPlaylist likedPlaylist = LikedPlaylist.builder().playlist(PlaylistConverter.convertToPlaylist(playlistRepository.findByPlaylistId(request.getPlaylistId())))
                    .user(UserConverter.convertToUser(userRepository.findUserById(request.getUserId()))).build();
            likedPlaylistsRepository.save(LikedPlaylistsConverter.ConvertToLikedPlaylistEntity(likedPlaylist));
        }
    }
}
