package com.example.individual.business;

import com.example.individual.domain.CheckIfPlaylistIsLikedRequest;
import com.example.individual.domain.CheckIfPlaylistIsLikedResponse;

public interface CheckIfPlaylistIsLikedUseCase {
    CheckIfPlaylistIsLikedResponse checkIfLiked(CheckIfPlaylistIsLikedRequest request);
}
