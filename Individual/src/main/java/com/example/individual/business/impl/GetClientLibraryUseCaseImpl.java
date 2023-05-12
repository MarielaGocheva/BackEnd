package com.example.individual.business.impl;

import com.example.individual.business.GetClientLibraryUseCase;
import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.business.exceptions.UserNotFoundException;
import com.example.individual.domain.GetClientLibraryRequest;
import com.example.individual.domain.GetClientLibraryResponse;
import com.example.individual.domain.Playlist;
import com.example.individual.repository.LikedPlaylistsRepository;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetClientLibraryUseCaseImpl implements GetClientLibraryUseCase {
    private UserRepository userRepository;
    private PlaylistRepository playlistRepository;
    private LikedPlaylistsRepository likedPlaylistsRepository;

    @Override
    public GetClientLibraryResponse getLibrary(GetClientLibraryRequest request) {
        if(userRepository.existsById(request.getUserId())){
            List<Playlist> liked = likedPlaylistsRepository.findAllByUser(userRepository.findUserById(request.getUserId()))
                    .stream().map(pl -> PlaylistConverter.convertToPlaylist(playlistRepository.findByPlaylistId(pl.getPlaylist().getPlaylistId()))).toList();
            return GetClientLibraryResponse.builder().playlists(liked).build();
        }
        else {
            throw new UserNotFoundException();
        }
    }
}
