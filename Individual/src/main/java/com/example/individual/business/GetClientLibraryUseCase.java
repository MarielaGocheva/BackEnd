package com.example.individual.business;

import com.example.individual.domain.GetClientLibraryRequest;
import com.example.individual.domain.GetClientLibraryResponse;

public interface GetClientLibraryUseCase {
    GetClientLibraryResponse getLibrary(GetClientLibraryRequest request);
}
