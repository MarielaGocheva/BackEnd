package com.example.individual.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetHistoryRequest {
    private Long userId;
}
