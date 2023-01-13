package com.example.individual.business.impl;

import com.example.individual.business.converter.SongConverter;
import com.example.individual.business.GetMostPlayedSongsUseCase;
import com.example.individual.domain.GetMostPlayedSongsResponse;
import com.example.individual.repository.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetMostPlayedSongsUseCaseImpl implements GetMostPlayedSongsUseCase {
    private SongRepository songRepository;

    @Override
    public GetMostPlayedSongsResponse getMostPlayed() {
        return GetMostPlayedSongsResponse.builder().mostPlayed(
                songRepository.findTop20ByOrderByPlaysDesc().stream().map(SongConverter::convertToSong).toList()).build();
    }
}
