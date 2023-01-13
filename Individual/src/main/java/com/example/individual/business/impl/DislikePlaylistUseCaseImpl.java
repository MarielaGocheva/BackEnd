package com.example.individual.business.impl;

import com.example.individual.business.DislikePlaylistUseCase;
import com.example.individual.business.exceptions.PlaylistDoesNotExist;
import com.example.individual.business.exceptions.UserNotFoundException;
import com.example.individual.domain.DislikePlaylistRequest;
import com.example.individual.repository.LikedPlaylistsRepository;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DislikePlaylistUseCaseImpl implements DislikePlaylistUseCase {
    private LikedPlaylistsRepository likedPlaylistsRepository;
    private PlaylistRepository playlistRepository;
    private UserRepository userRepository;

    @Override
    public void dislikePlaylist(DislikePlaylistRequest request) {
        if(!userRepository.existsById(request.getUserId())){
            throw new UserNotFoundException();
        }
        else if (!playlistRepository.existsById(request.getPlaylistId())){
            throw new PlaylistDoesNotExist();
        }
        else {
            likedPlaylistsRepository.deleteByUserAndPlaylist(userRepository.findUserById(request.getUserId()), playlistRepository.findByPlaylistId(request.getPlaylistId()));
        }
    }
}
