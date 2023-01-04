package com.example.individual.business;

import com.example.individual.domain.GetSearchResultsRequest;
import com.example.individual.domain.GetSearchResultsResponse;

public interface SearchUseCase {
    GetSearchResultsResponse getResults(GetSearchResultsRequest request);
}
