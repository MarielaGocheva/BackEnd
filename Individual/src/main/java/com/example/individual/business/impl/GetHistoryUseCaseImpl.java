package com.example.individual.business.impl;

import com.example.individual.business.GetHistoryUseCase;
import com.example.individual.business.converter.RecentlyPlayedConverter;
import com.example.individual.business.converter.SongConverter;
import com.example.individual.business.exceptions.UserNotFoundException;
import com.example.individual.domain.GetHistoryRequest;
import com.example.individual.domain.GetHistoryResponse;
import com.example.individual.domain.RecentlyPlayed;
import com.example.individual.domain.Song;
import com.example.individual.repository.PlaylistRepository;
import com.example.individual.repository.RecentlyPlayedRepository;
import com.example.individual.repository.SongRepository;
import com.example.individual.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetHistoryUseCaseImpl implements GetHistoryUseCase {
    private RecentlyPlayedRepository recentlyPlayedRepository;
    private SongRepository songRepository;
    private UserRepository userRepository;
    @Override
    public GetHistoryResponse getHistory(GetHistoryRequest request) {
        if(userRepository.existsById(request.getUserId())){
            List<RecentlyPlayed> history = recentlyPlayedRepository.findAllByUserIdOrderByIdDesc(request.getUserId()).stream()
                    .map(RecentlyPlayedConverter::convertToRecentlyPlayed).toList();
            List<Song> results = new ArrayList<>();
            for (RecentlyPlayed log : history){
                results.add(SongConverter.convertToSong(songRepository.findBySongId(log.getSongId())));
            }
            return GetHistoryResponse.builder().recentlyPlayed(results).build();
        }
        else {
            throw new UserNotFoundException();
        }
    }
}
