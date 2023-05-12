package com.example.individual.business;

import com.example.individual.domain.GetArtistPageInfoRequest;
import com.example.individual.domain.GetArtistPageInfoResponse;

public interface GetArtistPageInfoUseCase {
    GetArtistPageInfoResponse findArtist(GetArtistPageInfoRequest request);
}
