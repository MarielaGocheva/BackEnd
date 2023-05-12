package com.example.individual.business;

import com.example.individual.domain.GetHistoryRequest;
import com.example.individual.domain.GetHistoryResponse;

public interface GetHistoryUseCase {
    GetHistoryResponse getHistory(GetHistoryRequest request);
}
