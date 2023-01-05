package com.example.individual.business.impl;

import com.example.individual.business.GetArtistPageInfoUseCase;
import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.business.converter.UserConverter;
import com.example.individual.business.exceptions.UserNotFoundException;
import com.example.individual.domain.GetArtistPageInfoRequest;
import com.example.individual.domain.GetArtistPageInfoResponse;
import com.example.individual.domain.Playlist;
import com.example.individual.domain.User;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetArtistPageInfoUseCaseImpl implements GetArtistPageInfoUseCase {
    private UserRepository userRepository;
    private PlaylistRepository playlistRepository;
    @Override
    public GetArtistPageInfoResponse findArtist(GetArtistPageInfoRequest request) {
        if(request.getId() != null){
            User user = UserConverter.convertToUser(userRepository.findUserById(request.getId()));
            String img = "shorturl.at/cmAPV";
            List<Playlist> playlists = playlistRepository.findAllByUserId(request.getId()).stream().map(playlistEntity -> PlaylistConverter.convertToPlaylist(playlistEntity)).toList();
            return GetArtistPageInfoResponse.builder().fName(user.getFName()).lName(user.getLName()).img(img).playlists(playlists).build();
        }
        else {
            throw new UserNotFoundException();
        }
    }
}
