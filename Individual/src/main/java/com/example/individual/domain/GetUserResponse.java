package com.example.individual.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
public class GetUserResponse {
    @Getter
    private User user;
}
