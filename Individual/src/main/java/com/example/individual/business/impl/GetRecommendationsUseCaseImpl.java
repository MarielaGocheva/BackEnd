package com.example.individual.business.impl;

import com.example.individual.business.GetRecommendationsUseCase;
import com.example.individual.business.converter.PlaylistConverter;
import com.example.individual.business.exceptions.InsufficientNumberOfLikedPlaylists;
import com.example.individual.business.exceptions.UserNotFoundException;
import com.example.individual.domain.GetRecommendationsRequest;
import com.example.individual.domain.GetRecommendationsResponse;
import com.example.individual.repository.LikedPlaylistsRepository;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetRecommendationsUseCaseImpl implements GetRecommendationsUseCase {
    private UserRepository userRepository;
    private PlaylistRepository playlistRepository;

    @Override
    public GetRecommendationsResponse getRecommendations(GetRecommendationsRequest request) {
        if(!userRepository.existsById(request.getUserId())){
            throw new UserNotFoundException();
        }
        else {
            try{
                List<Long> res = playlistRepository.findMostLikedGenres(request.getUserId());
                List<Long> recommendedPlaylists;
                if(res.size() == 3){
                    recommendedPlaylists = playlistRepository.findBestMatchingPlaylists(res.get(0), res.get(1), res.get(2), request.getUserId());
                }
                else if (res.size() == 2){
                    recommendedPlaylists = playlistRepository.findBestMatchingPlaylists(res.get(0), res.get(1), res.get(0), request.getUserId());
                }
                else {
                    recommendedPlaylists = playlistRepository.findBestMatchingPlaylists(res.get(0), res.get(0), res.get(0), request.getUserId());
                }
                System.out.println("RECOMMENDED " + recommendedPlaylists);
                return GetRecommendationsResponse.builder().recommended(recommendedPlaylists.stream().map(id -> PlaylistConverter.convertToPlaylist(playlistRepository.findByPlaylistId(id))).toList()).build();
            }
            catch (IndexOutOfBoundsException e){
                return GetRecommendationsResponse.builder().build();
            }

        }
    }
}
