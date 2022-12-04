package com.example.individual.business;

import com.example.individual.domain.SwitchSongPositionRequest;
import com.example.individual.domain.SwitchSongPositionResponse;

public interface SwitchSongPositionUseCase {
    SwitchSongPositionResponse switchPosition(SwitchSongPositionRequest request);
}
