package com.example.individual.business.impl;

import com.example.individual.business.CheckIfPlaylistIsLikedUseCase;
import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.business.converter.UserConverter;
import com.example.individual.business.exceptions.PlaylistDoesNotExist;
import com.example.individual.business.exceptions.UserNotFoundException;
import com.example.individual.domain.CheckIfPlaylistIsLikedRequest;
import com.example.individual.domain.CheckIfPlaylistIsLikedResponse;
import com.example.individual.repository.LikedPlaylistsRepository;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckIfPlaylistIsLikedUseCaseImpl implements CheckIfPlaylistIsLikedUseCase {
    private LikedPlaylistsRepository likedPlaylistsRepository;
    private PlaylistRepository playlistRepository;
    private UserRepository userRepository;

    @Override
    public CheckIfPlaylistIsLikedResponse checkIfLiked(CheckIfPlaylistIsLikedRequest request) {
        if(!userRepository.existsById(request.getUserId())){
            throw new UserNotFoundException();
        }
        else if (!playlistRepository.existsById(request.getPlaylistId())){
            throw new PlaylistDoesNotExist();
        }
        else {
            return CheckIfPlaylistIsLikedResponse.builder().liked(likedPlaylistsRepository.existsByUserAndPlaylist(
                   userRepository.findUserById(request.getUserId()), playlistRepository.findByPlaylistId(request.getPlaylistId()))).build();
        }
    }
}
